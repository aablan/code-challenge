package com.emil.codechallenge.datagenerator.service;

import com.emil.codechallenge.common.data.GPSCoordinates;
import com.emil.codechallenge.common.data.temperature.TemperatureData;
import java.time.Instant;
import java.util.Random;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


/**
 * Service class to push randomly generated data to kafka broker.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class TemperatureDataGeneratorService {

  private final KafkaTemplate<String, TemperatureData> kafkaTemperatureTemplate;

  @PostConstruct
  public void generateData() throws InterruptedException {

    while (true) {
      val temperatureData = TemperatureData.builder()
          .temperature(generateRandomTemperature())
          .coordinates(generateRandomCoordinates())
          .captureTime(Instant.now())
          .build();
      log.info(
          "Sending Temperature value of {} at location {}",
          temperatureData.getTemperature(),
          temperatureData.getCoordinates()
      );
      kafkaTemperatureTemplate
          .send("temperature_data", temperatureData)
          .completable()
          .exceptionally(it -> {
            log.error("Unable to send data object {}", temperatureData);
            return null;
          });
      Thread.sleep(5000);
    }
  }

  private double generateRandomTemperature() {
    Random random = new Random();
    return Math.round(random.nextFloat() * 1000) / 10.0;
  }

  private GPSCoordinates generateRandomCoordinates() {
    Random random = new Random();
    val latitude = random.nextFloat() * 100;
    val longitude = random.nextFloat() * 100;
    return new GPSCoordinates(latitude, longitude);
  }
}
