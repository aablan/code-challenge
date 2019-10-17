/*
 * Copyright (C) Siemens AG 2019 ALL RIGHTS RESERVED.
 */

package com.emil.codechallenge.datagenerator.utils;

import static com.emil.codechallenge.datagenerator.utils.CborUtils.cborWriter;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Slf4j
public class KafkaUtils {


  public static <T> Serializer<T> kafkaSerializerWithCborCodec(Class<T> clazz) {
    return new Serializer<T>() {
      @Override
      public void configure(Map<String, ?> configs, boolean isKey) {
      }

      @Override
      public byte[] serialize(String topic, T data) {
        val writer = cborWriter(clazz);
        try {
          return writer.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
          log.error("Failed to serialize object of type " + clazz.getName(), e);
        }
        return null;
      }

      @Override
      public void close() {
      }
    };
  }

//  public static <T> Deserializer<T> kafkaDeserializerWithCborCodec(Class<T> clazz) {
//    return new Deserializer<T>() {
//      @Override
//      public void configure(Map<String, ?> configs, boolean isKey) {
//
//      }
//
//      @Override
//      public T deserialize(String topic, byte[] data) {
//        val reader = cborReader(clazz);
//        try {
//          return reader.readValue(data);
//        } catch (IOException e) {
//          log.error("Failed to read data for object of type " + clazz.getName(), e);
//        }
//        return null;
//      }
//
//      @Override
//      public void close() {
//
//      }
//    };
//  }
}
