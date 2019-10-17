package com.emil.codechallenge.datagenerator.service;

import com.emil.codechallenge.common.model.commons.GPSCoordinates;
import com.emil.codechallenge.common.model.temp.TemperatureData;
import java.time.Instant;
import java.util.Random;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class DataGeneratorService {

  private final KafkaTemplate<String, TemperatureData> kafkaTemperatureTemplate;

  @PostConstruct
  public void generateData() throws InterruptedException {

    while (true) {
      val temp = generateRandomTemperature();
      val coordinates = generateRandomCoordinates();
      log.info("Sending Temperature value of {} at location {}", temp, coordinates);
      kafkaTemperatureTemplate
          .send(
              "temperature_data",
              TemperatureData.builder()
                  .temperature(temp)
                  .coordinates(coordinates)
                  .captureTime(Instant.now())
                  .build()
          );
      Thread.sleep(5000);
    }
  }

  private float generateRandomTemperature() {
    Random random = new Random();
    return Math.round(random.nextFloat() * 1000) / 10.0f;
  }

  private GPSCoordinates generateRandomCoordinates() {
    Random random = new Random();
    val latitude = random.nextFloat() * 100;
    val longitude = random.nextFloat() * 100;
    return new GPSCoordinates(latitude, longitude);
  }
}
