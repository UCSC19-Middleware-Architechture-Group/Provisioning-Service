package com.sritel.provisioning.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.sritel.provisioning.Models.ServiceActivation;

import java.util.List;

@Repository
public interface ServiceActivationRepository extends MongoRepository<ServiceActivation, String> {
    List<ServiceActivation> findByAccountIdAndStatus(String accountId, String activated);

//    List<ServiceActivation> findByAccountIdAndStatus(String accountId, String status);

}
