package com.emil.codechallenge.web.repository.mongo;

import com.emil.codechallenge.common.dao.TemperatureDataEntity;
import com.emil.codechallenge.common.data.temperature.TemperatureData;
import com.emil.codechallenge.web.repository.TemperatureDataRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * Repository class that implements the functionality required by
 *
 * @see TemperatureDataRepository as mongo operations by facilitating
 * @see TemperatureDataRepositoryMongo
 */
@RequiredArgsConstructor
@Repository
public class TemperatureDataRepositoryMongoCustom
    implements TemperatureDataRepository {

  private final TemperatureDataRepositoryMongo temperatureDataRepositoryMongo;

  @Override
  public List<TemperatureData> allTemperatureData() {
    return temperatureDataRepositoryMongo
        .findAll()
        .stream()
        .map(TemperatureDataEntity::toCommon)
        .collect(Collectors.toList());
  }
}
