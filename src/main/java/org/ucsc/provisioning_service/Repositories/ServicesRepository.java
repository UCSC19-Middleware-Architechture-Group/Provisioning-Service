package org.ucsc.provisioning_service.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.ucsc.provisioning_service.Models.Services;

@Repository
public interface ServicesRepository extends MongoRepository<Services, String> {
}
