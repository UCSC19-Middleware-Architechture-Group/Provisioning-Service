package com.sritel.provisioning.Services;

import com.sritel.provisioning.Models.Services;
import com.sritel.provisioning.Repositories.ServiceActivationRepository;
import com.sritel.provisioning.Repositories.ServicesRepository;
import com.sritel.provisioning.client.AccountClient;
import com.sritel.provisioning.event.ServiceEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.sritel.provisioning.Models.ServiceActivation;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceActivationService {
    private final ServiceActivationRepository serviceActivationRepository;
    private final ServicesRepository servicesRepository;
    private final AccountClient accountClient;
    private final KafkaTemplate<String, ServiceEvent> kafkaTemplate;

    public List<ServiceActivation> activatedServiceList(String email) {
        return serviceActivationRepository.findByEmailAndStatus(email, "activated");
    }

    public String activateService(ServiceActivation service) {
        if (service.getServiceId() == null) {
            return "Service ID is missing";
        }
        if (service.getEmail() == null) {
            return "Email is missing";
        }
        service.setActivationDate(LocalDate.now().toString());
        service.setStatus("activated");

        // Check account is existing by account service
        var isAccountExists = accountClient.isAccountExists(service.getPhoneNo());
        if (!isAccountExists) {
            return "Account does not exist";
        }

        serviceActivationRepository.save(service);

        Services services = servicesRepository.findById(service.getServiceId()).orElseThrow(() -> new RuntimeException("Service not found"));

        ServiceEvent serviceEvent = serviceToServiceEvent(service, services);
        log.info("Start - Sending ActivateServiceEvent {} to kafka topic activate-service", serviceEvent);
        kafkaTemplate.send("activate-service", serviceEvent);
        log.info("End - Sending ActivateServiceEvent {} to kafka topic activate-service", serviceEvent);

        return "Service activated successfully";
    }

    public String deactivateService(String id) {
        ServiceActivation service = serviceActivationRepository.findById(id).orElse(null);
        if (service == null) {
            return "Service not found";
        }
        service.setDeactivatedDate(LocalDate.now().toString());
        service.setStatus("deactivated");

        serviceActivationRepository.save(service);

        Services services = servicesRepository.findById(service.getServiceId()).orElseThrow(() -> new RuntimeException("Service not found"));

        ServiceEvent serviceEvent = serviceToServiceEvent(service, services);
        log.info("Start - Sending DeactivateServiceEvent {} to kafka topic deactivate-service", serviceEvent);
        kafkaTemplate.send("deactivate-service", serviceEvent);
        log.info("End - Sending DeactivateServiceEvent {} to kafka topic deactivate-service", serviceEvent);

        return "Service deactivated successfully";
    }


    private static ServiceEvent serviceToServiceEvent(ServiceActivation service, Services services) {
        ServiceEvent serviceEvent = new ServiceEvent();

        serviceEvent.setServiceId(service.getServiceId());
        serviceEvent.setEmail(service.getEmail());
        serviceEvent.setActivationDate(LocalDate.now().toString());
        serviceEvent.setStatus(service.getStatus());
        serviceEvent.setServiceName(services.getServiceName());
        serviceEvent.setServiceType(services.getServiceType());
        serviceEvent.setServiceDescription(services.getServiceDescription());
        serviceEvent.setServiceCategory(services.getServiceCategory());
        serviceEvent.setServiceCharge(services.getServiceCharge());

        return serviceEvent;
    }
}
