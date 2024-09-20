package org.ucsc.provisioning_service.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ucsc.provisioning_service.Models.Services;
import org.ucsc.provisioning_service.Services.ServicesService;

@RestController
@CrossOrigin
@RequestMapping(path = "api/Provisioning_Service/service")
@AllArgsConstructor
public class ServicesController {
    private final ServicesService servicesService;
    @PostMapping("/add")
    public String addService(@RequestBody Services service) {
        return servicesService.addService(service);
    }
}
