package com.emil.codechallenge.dataprocessor.repository;

import com.emil.codechallenge.dataprocessor.repository.dao.TemperatureDataEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TempDataRepositoryMongo extends
    MongoRepository<TemperatureDataEntity, ObjectId> {

}
