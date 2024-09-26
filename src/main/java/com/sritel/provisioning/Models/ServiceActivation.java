package com.sritel.provisioning.Models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "ServiceActivation")
public class ServiceActivation {
    private String id;
    private String serviceId;
    private String email;
    private String phoneNo;
    private String activationDate;
    private String deactivatedDate;
    private String status;
}
