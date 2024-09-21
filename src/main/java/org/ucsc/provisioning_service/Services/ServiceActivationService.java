package org.ucsc.provisioning_service.Services;

import org.springframework.stereotype.Service;
import org.ucsc.provisioning_service.Models.ServiceActivation;
import org.ucsc.provisioning_service.Models.Services;
import org.ucsc.provisioning_service.Repositories.ServiceActivationRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceActivationService {
    private final ServiceActivationRepository serviceActivationRepository;

    public ServiceActivationService(ServiceActivationRepository serviceActivationRepository) {
        this.serviceActivationRepository = serviceActivationRepository;
    }
    public List<ServiceActivation> activatedServiceList(String accountId) {
        return serviceActivationRepository.findByAccountIdAndStatus(accountId, "activated");
    }

    public String activateService(ServiceActivation service) {
        if(service.getServiceId() == null) {
            return "Service ID is missing";
        }
        if(service.getAccountId() == null) {
            return "Account ID is missing";
        }
        service.setActivationDate(LocalDate.now().toString());
        service.setStatus("activated");

        serviceActivationRepository.save(service);
        return "Service activated successfully";
    }

    public String deactivateService(String id) {
        ServiceActivation service = serviceActivationRepository.findById(id).orElse(null);
        if(service == null) {
            return "Service not found";
        }
        service.setDeactivatedDate(LocalDate.now().toString());
        service.setStatus("deactivated");

        serviceActivationRepository.save(service);
        return "Service deactivated successfully";
    }
}
