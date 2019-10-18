package com.emil.codechallenge.web.service;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import com.emil.codechallenge.common.dao.TemperatureDataEntity;
import com.emil.codechallenge.common.data.GPSCoordinates;
import com.emil.codechallenge.web.config.TestConfig;
import com.emil.codechallenge.web.repository.mongo.TemperatureDataRepositoryMongo;
import com.google.common.collect.ImmutableList;
import java.time.Instant;
import java.util.List;
import lombok.val;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Import(TestConfig.class)
@ExtendWith(SpringExtension.class)
class TemperatureDataServiceTest {

  @Autowired
  private TemperatureDataService temperatureDataService;

  @Autowired
  private TemperatureDataRepositoryMongo temperatureDataRepositoryMongo;

  private static final List<TemperatureDataEntity> initialTestData = ImmutableList.of(
      TemperatureDataEntity.builder()
          .id(new ObjectId("000000000000000000000001"))
          .temperature(25.3)
          .coordinates(new GPSCoordinates(0.0f, 0.0f))
          .captureTime(Instant.ofEpochMilli(1))
          .build(),
      TemperatureDataEntity.builder()
          .id(new ObjectId("000000000000000000000002"))
          .temperature(37.5)
          .coordinates(new GPSCoordinates(25.66666f, 53.111111f))
          .captureTime(Instant.ofEpochMilli(2))
          .build(),
      TemperatureDataEntity.builder()
          .id(new ObjectId("000000000000000000000003"))
          .temperature(17.35)
          .coordinates(new GPSCoordinates(0.1f, 0.5f))
          .captureTime(Instant.ofEpochMilli(3))
          .build()
  );

  @Test
  void temperatureDataServiceTest() {
    Mockito.when(temperatureDataRepositoryMongo.findAll())
        .thenReturn(initialTestData);
    val expectedDataList = initialTestData.stream()
        .map(TemperatureDataEntity::toCommon)
        .collect(toList());
    assertThat(temperatureDataService.temperatureData())
        .containsAll(expectedDataList);
  }
}
