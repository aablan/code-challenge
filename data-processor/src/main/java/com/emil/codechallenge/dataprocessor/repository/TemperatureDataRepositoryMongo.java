package com.emil.codechallenge.dataprocessor.repository;

import com.emil.codechallenge.common.dao.TemperatureDataEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TemperatureDataRepositoryMongo extends
    MongoRepository<TemperatureDataEntity, ObjectId> {

}
