package com.emil.codechallenge.web.repository.mongo;

import com.emil.codechallenge.common.dao.TemperatureDataEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TemperatureDataRepositoryMongo extends
    MongoRepository<TemperatureDataEntity, ObjectId> {

}
