package com.emil.codechallenge.dataprocessor.service.persistor;

import static org.assertj.core.api.Assertions.assertThat;

import com.emil.codechallenge.common.dao.TemperatureDataEntity;
import com.emil.codechallenge.common.data.GPSCoordinates;
import com.emil.codechallenge.common.data.temperature.TemperatureData;
import com.emil.codechallenge.dataprocessor.config.EmbeddedMongoContainerConfig;
import com.emil.codechallenge.dataprocessor.repository.TemperatureDataRepositoryMongo;
import com.emil.codechallenge.dataprocessor.service.persister.temperature.TemperatureDataPersister;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.shaded.com.google.common.collect.ImmutableList;

@ActiveProfiles("embedded_mongo")
@ExtendWith(SpringExtension.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.NONE,
    classes = {
        EmbeddedMongoContainerConfig.class,
    }
)
class DataPersistenceIntegrationTest {

  @Autowired
  private TemperatureDataPersister dataPersister;

  @Autowired
  private TemperatureDataRepositoryMongo dataRepository;

  @Autowired
  private MongoTemplate mongoTemplate;

  private final List<TemperatureData> initialTestData = ImmutableList.of(
      TemperatureData.builder()
          .id("000000000000000000000001")
          .temperature(25.3)
          .coordinates(new GPSCoordinates(0.0f, 0.0f))
          .captureTime(Instant.ofEpochMilli(1))
          .build(),
      TemperatureData.builder()
          .id("000000000000000000000002")
          .temperature(37.5)
          .coordinates(new GPSCoordinates(25.66666f, 53.111111f))
          .captureTime(Instant.ofEpochMilli(2))
          .build(),
      TemperatureData.builder()
          .id("000000000000000000000003")
          .temperature(17.35)
          .coordinates(new GPSCoordinates(0.1f, 0.5f))
          .captureTime(Instant.ofEpochMilli(3))
          .build()
  );

  @Test
  void testDataPersistence() {
    initialTestData.forEach(it -> dataPersister.save(it));
    val persistedData = dataRepository.findAll()
        .stream()
        .map(TemperatureDataEntity::toCommon)
        .collect(Collectors.toList());

    assertThat(persistedData).containsAll(initialTestData);
  }
}
