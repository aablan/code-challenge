package com.emil.codechallenge.dataprocessor.service.persister.temperature.mongo;

import com.emil.codechallenge.common.model.temp.TemperatureData;
import com.emil.codechallenge.dataprocessor.repository.TemperatureDataRepositoryMongo;
import com.emil.codechallenge.dataprocessor.repository.dao.TemperatureDataEntity;
import com.emil.codechallenge.dataprocessor.service.persister.temperature.TemperatureDataPersister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Temperature data point persister implementation for Mongo
 */
@RequiredArgsConstructor
@Component
public class TemperatureDataPersisterMongoImpl implements TemperatureDataPersister {

  private final TemperatureDataRepositoryMongo tempDataRepositoryMongo;

  @Override
  public void save(TemperatureData data) {
    tempDataRepositoryMongo.save(
        TemperatureDataEntity.from(data)
    );
  }
}
