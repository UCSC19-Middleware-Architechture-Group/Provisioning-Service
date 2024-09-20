package org.ucsc.provisioning_service.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ucsc.provisioning_service.Models.Services;
import org.ucsc.provisioning_service.Services.ServicesService;

import java.util.List;

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

    @GetMapping("/allServices")
    public List<Services> getAllServices() {
        return servicesService.getAllServices();
    }

    @GetMapping("/service/{id}")
    public Services getService(@PathVariable String id) {
        return servicesService.getService(id);
    }

    @PutMapping("/update")
    public String updateService(@RequestBody Services service) {
        return servicesService.updateService(service);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteService(@PathVariable String id) {
        return servicesService.deleteService(id);
    }




}
