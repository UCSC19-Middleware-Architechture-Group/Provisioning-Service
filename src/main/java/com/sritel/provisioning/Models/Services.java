    package com.sritel.provisioning.Models;

    import lombok.Data;
    import org.springframework.data.mongodb.core.mapping.Document;

    @Data
    @Document(collection = "Services")
    public class Services {
        private String id;
        private String serviceName;
        private String serviceType;
        private String serviceDescription;
        private String serviceStatus;
        private String serviceCategory;
        private String createdDate;
        private String updatedDate;
        private float serviceCharge;
    }
