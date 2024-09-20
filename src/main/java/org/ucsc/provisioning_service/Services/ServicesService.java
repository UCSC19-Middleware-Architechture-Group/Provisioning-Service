package org.ucsc.provisioning_service.Services;

import org.springframework.stereotype.Service;
import org.ucsc.provisioning_service.Models.Services;
import org.ucsc.provisioning_service.Repositories.ServicesRepository;

import java.time.LocalDate;
import java.util.List;

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

        service.setServiceStatus("Active");
        service.setCreatedDate(LocalDate.now().toString());
        service.setUpdatedDate(LocalDate.now().toString());
        servicesRepository.save(service);
        return "Service added successfully";
    }

    public List<Services> getAllServices() {
        return servicesRepository.findAll();
    }

    public Services getService(String id) {
        return servicesRepository.findById(id).orElse(null);
    }

    public String updateService(Services service) {
        Services existingService = servicesRepository.findById(service.getId()).orElse(null);
        if(existingService == null) {
            return "Service not found";
        }

        if(service.getServiceName() != null || !service.getServiceName().isEmpty()) {
            existingService.setServiceName(service.getServiceName());
        }
        if(service.getServiceType() != null || !service.getServiceType().isEmpty()) {
            existingService.setServiceType(service.getServiceType());
        }
        if(service.getServiceDescription() != null || !service.getServiceDescription().isEmpty()) {
            existingService.setServiceDescription(service.getServiceDescription());
        }
        if(service.getServiceCategory() != null || !service.getServiceCategory().isEmpty()) {
            existingService.setServiceCategory(service.getServiceCategory());
        }
        if(service.getServiceCharge() >= 0) {
            existingService.setServiceCharge(service.getServiceCharge());
        }
        existingService.setServiceStatus(service.getServiceStatus());
        existingService.setUpdatedDate(LocalDate.now().toString());
        servicesRepository.save(existingService);
        return "Service updated successfully";
    }

    public String deleteService(String id) {
        Services existingService = servicesRepository.findById(id).orElse(null);
        if(existingService == null) {
            return "Service not found";
        }
        servicesRepository.deleteById(id);
        return "Service deleted successfully";
    }
}
