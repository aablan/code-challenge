package com.emil.codechallenge.common.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class CborUtils {

  private static final ObjectMapper cborMapper = defaultObjectMapper(new CBORFactory());


  public static <T> ObjectWriter cborWriter(Class<T> clazz) {
    return cborMapper.writer()
        .forType(clazz);
  }

  public static <T> ObjectReader cborReader(Class<T> clazz) {
    return cborMapper.reader()
        .forType(clazz);
  }

  private static ObjectMapper defaultObjectMapper(JsonFactory jsonFactory) {
    return new ObjectMapper(jsonFactory)
        .registerModule(new JavaTimeModule())
        .registerModule(new Jdk8Module())
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true)
        .configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
        .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
        .configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }
}
