package org.ucsc.provisioning_service.Models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Services")
public class Services {
    private String serviceId;
    private String serviceName;
    private String serviceType;
    private String serviceDescription;
    private String serviceStatus;
    private String serviceCategory;
    private String createdDate;
    private String updatedDate;
    private String validFrom;
    private String validTo;
    private float serviceCharge;
}
