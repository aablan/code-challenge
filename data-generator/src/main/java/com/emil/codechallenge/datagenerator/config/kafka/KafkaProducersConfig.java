/*
 * Copyright (C) Siemens AG 2019 ALL RIGHTS RESERVED.
 */

package com.emil.codechallenge.datagenerator.config.kafka;

import com.emil.codechallenge.common.model.temp.TemperatureData;
import com.emil.codechallenge.datagenerator.utils.KafkaUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@AllArgsConstructor
class KafkaProducersConfig {

  private final KafkaProperties kafkaProperties;

  @Bean
  public ProducerFactory<String, TemperatureData> kafkaTempDataProducerFactory() {
    val kafkaTempDataProducerFactory =
        new DefaultKafkaProducerFactory<String, TemperatureData>(
            kafkaProperties.buildProducerProperties()
        );
    kafkaTempDataProducerFactory.setKeySerializer(new StringSerializer());
    kafkaTempDataProducerFactory.setValueSerializer(
        KafkaUtils.kafkaSerializerWithCborCodec(TemperatureData.class)
    );
    return kafkaTempDataProducerFactory;
  }

  @Bean
  public KafkaTemplate<String, TemperatureData> kafkaTemperatureTemplate(
      ProducerFactory<String, TemperatureData> producerFactory
  ) {
    return new KafkaTemplate<>(producerFactory);
  }
}
