package com.emil.codechallenge.dataprocessor.service.persister.temp.mongo;

import com.emil.codechallenge.common.model.temp.TemperatureData;
import com.emil.codechallenge.dataprocessor.repository.TempDataRepositoryMongo;
import com.emil.codechallenge.dataprocessor.repository.dao.TemperatureDataEntity;
import com.emil.codechallenge.dataprocessor.service.persister.temp.TemperatureDataPersister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TemperatureDataPersisterMongoImpl implements TemperatureDataPersister {

  private final TempDataRepositoryMongo tempDataRepositoryMongo;

  @Override
  public void save(TemperatureData data) {
    tempDataRepositoryMongo.save(
        TemperatureDataEntity.from(data)
    );
  }
}
