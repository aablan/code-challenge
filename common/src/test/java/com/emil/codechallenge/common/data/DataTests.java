package com.emil.codechallenge.common.data;

import static com.emil.codechallenge.common.model.utils.CborUtils.cborReader;
import static com.emil.codechallenge.common.model.utils.CborUtils.cborWriter;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.emil.codechallenge.common.model.commons.GPSCoordinates;
import com.emil.codechallenge.common.model.temp.TemperatureData;
import java.io.IOException;
import java.time.Instant;
import lombok.val;
import org.junit.jupiter.api.Test;

class DataTests {

  private static GPSCoordinates RANDOM_COORDINATES = new GPSCoordinates(25.2f, 55.1f);

  @Test
  void testSensorData_SERDE_WithCbor() throws IOException {

    val originalData = TemperatureData.builder()
        .temperature(37)
        .coordinates(RANDOM_COORDINATES)
        .captureTime(Instant.now())
        .build();
    val serializedData = cborWriter(TemperatureData.class)
        .writeValueAsBytes(originalData);
    val deserializedData = cborReader(TemperatureData.class)
        .readValue(serializedData);
    assertEquals(originalData, deserializedData);
  }
}
