package org.ucsc.provisioning_service.Services;

import org.springframework.stereotype.Service;
import org.ucsc.provisioning_service.Models.Services;
import org.ucsc.provisioning_service.Repositories.ServicesRepository;

import java.time.LocalDate;

@Service
public class ServicesService {
    private final ServicesRepository servicesRepository;

    public ServicesService(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    public String addService(Services service) {
        if(service.getServiceName() == null || service.getServiceName().isEmpty()) {
            return "Service name cannot be empty";
        }
        if(service.getServiceType() == null || service.getServiceType().isEmpty()) {
            return "Service type cannot be empty";
        }
        if(service.getServiceDescription() == null || service.getServiceDescription().isEmpty()) {
            return "Service description cannot be empty";
        }
        if(service.getServiceCategory() == null || service.getServiceCategory().isEmpty()) {
            return "Service category cannot be empty";
        }
        if(service.getServiceCharge() < 0) {
            return "Service charge cannot be negative";
        }
        if(service.getValidFrom() == null || service.getValidFrom().isEmpty()) {
            service.setValidFrom(LocalDate.now().toString());
        }

        service.setCreatedDate(LocalDate.now().toString());
        service.setUpdatedDate(LocalDate.now().toString());
        servicesRepository.save(service);
        return "Service added successfully";
    }
}
