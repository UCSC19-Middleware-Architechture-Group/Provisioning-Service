package org.ucsc.provisioning_service.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.ucsc.provisioning_service.Models.ServiceActivation;
import org.ucsc.provisioning_service.Models.Services;

import java.util.List;

@Repository
public interface ServiceActivationRepository extends MongoRepository<ServiceActivation, String> {
    List<ServiceActivation> findByAccountIdAndStatus(String accountId, String activated);

//    List<ServiceActivation> findByAccountIdAndStatus(String accountId, String status);

}
