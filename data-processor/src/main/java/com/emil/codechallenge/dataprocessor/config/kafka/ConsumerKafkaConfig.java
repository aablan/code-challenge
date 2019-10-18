package com.emil.codechallenge.dataprocessor.config.kafka;


import com.emil.codechallenge.common.data.temperature.TemperatureData;
import com.emil.codechallenge.common.utils.KafkaUtils;
import lombok.AllArgsConstructor;
import lombok.val;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@AllArgsConstructor
@EnableKafka
@EnableConfigurationProperties(KafkaProperties.class)
@Configuration
public class ConsumerKafkaConfig {

  private final KafkaProperties kafkaProperties;

  @Bean
  public KafkaListenerContainerFactory kafkaTempDataContainerFactory() {
    return prepareKafkaContainerFactoryWithCustomDeserializer(TemperatureData.class);
  }

  private <T> KafkaListenerContainerFactory prepareKafkaContainerFactoryWithCustomDeserializer(
      Class<T> clazz
  ) {
    val kafkaCustomListenerContainerFactory =
        new ConcurrentKafkaListenerContainerFactory<String, T>();
    kafkaCustomListenerContainerFactory.setConsumerFactory(
        kafkaCustomDeserializerConsumerFactory(clazz)
    );
    return kafkaCustomListenerContainerFactory;
  }

  private <T> ConsumerFactory<String, T> kafkaCustomDeserializerConsumerFactory(Class<T> clazz) {
    val kafkaCustomDeserializerConsumerFactory =
        new DefaultKafkaConsumerFactory<String, T>(
            kafkaProperties.buildConsumerProperties()
        );
    kafkaCustomDeserializerConsumerFactory.setKeyDeserializer(new StringDeserializer());
    kafkaCustomDeserializerConsumerFactory.setValueDeserializer(
        KafkaUtils.kafkaDeserializerWithCborCodec(clazz)
    );
    return kafkaCustomDeserializerConsumerFactory;
  }

}
