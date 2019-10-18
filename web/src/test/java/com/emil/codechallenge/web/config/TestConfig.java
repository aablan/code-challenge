package com.emil.codechallenge.web.config;

import com.emil.codechallenge.web.repository.mongo.TemperatureDataRepositoryMongo;
import com.emil.codechallenge.web.repository.mongo.TemperatureDataRepositoryMongoCustom;
import com.emil.codechallenge.web.service.TemperatureDataService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestConfig {

  @Bean
  public TemperatureDataRepositoryMongoCustom temperatureDataRepositoryMongoCustom(
      TemperatureDataRepositoryMongo temperatureDataRepositoryMongo
  ) {
    return new TemperatureDataRepositoryMongoCustom(temperatureDataRepositoryMongo);
  }

  @Bean
  public TemperatureDataRepositoryMongo temperatureDataRepositoryMongo() {
    return Mockito.mock(TemperatureDataRepositoryMongo.class);
  }

  @Primary
  @Bean
  public TemperatureDataService temperatureDataService(
      TemperatureDataRepositoryMongoCustom temperatureDataRepositoryMongoCustom
  ) {
    return new TemperatureDataService(temperatureDataRepositoryMongoCustom);
  }
}
