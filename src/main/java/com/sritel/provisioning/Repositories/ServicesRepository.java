package com.sritel.provisioning.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.sritel.provisioning.Models.Services;

@Repository
public interface ServicesRepository extends MongoRepository<Services, String> {
}
