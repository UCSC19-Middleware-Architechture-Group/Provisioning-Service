package com.sritel.provisioning.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.sritel.provisioning.Models.ServiceActivation;
import com.sritel.provisioning.Services.ServiceActivationService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/service_activation")
@AllArgsConstructor
public class ServiceActivationController {
    private final ServiceActivationService serviceActivationService;
    @GetMapping("/service_details/{email}")
    public List<ServiceActivation> getServiceDetails(@PathVariable String email) {
        return serviceActivationService.activatedServiceList(email);
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
