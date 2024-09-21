package org.ucsc.provisioning_service.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ucsc.provisioning_service.Models.ServiceActivation;
import org.ucsc.provisioning_service.Models.Services;
import org.ucsc.provisioning_service.Services.ServiceActivationService;
import org.ucsc.provisioning_service.Services.ServicesService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/Provisioning_Service/service_activation")
@AllArgsConstructor
public class ServiceActivationController {
    private final ServiceActivationService serviceActivationService;
    @GetMapping("/service_details/{id}")
    public List<ServiceActivation> getServiceDetails(@PathVariable String id) {
        return serviceActivationService.activatedServiceList(id);
    }

    @PostMapping("/activate_service")
    public String activateService(@RequestBody ServiceActivation service) {
        return serviceActivationService.activateService(service);
    }

    @PutMapping("/deativate_service/{id}")
    public String deactivateService(@PathVariable String id) {
        return serviceActivationService.deactivateService(id);
    }

}
