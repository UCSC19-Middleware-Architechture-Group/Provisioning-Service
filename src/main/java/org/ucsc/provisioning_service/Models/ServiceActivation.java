package org.ucsc.provisioning_service.Models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "ServiceActivation")
public class ServiceActivation {
    private String id;
    private String serviceId;
    private String accountId;
    private String activationDate;
    private String deactivatedDate;
    private String status;
}
