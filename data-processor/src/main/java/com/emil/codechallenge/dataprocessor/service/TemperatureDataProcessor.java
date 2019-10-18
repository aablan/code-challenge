package com.emil.codechallenge.dataprocessor.service;

import com.emil.codechallenge.common.data.temperature.TemperatureData;
import com.emil.codechallenge.dataprocessor.service.persister.temperature.TemperatureDataPersister;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class TemperatureDataProcessor {

  private final TemperatureDataPersister temperatureDataPersister;

  @KafkaListener(
      topics = {"temperature_data"},
      groupId = "temperature_data_processor",
      containerFactory = "kafkaTempDataContainerFactory"
  )
  public void onNewTemperatureData(ConsumerRecord<String, TemperatureData> record) {
    val temperatureDataPoint = record.value();
    log.debug("Saving temperature object {}", temperatureDataPoint);
    temperatureDataPersister.save(temperatureDataPoint);
    log.debug("Temperature object {} saved successfully", temperatureDataPoint);
  }
}
