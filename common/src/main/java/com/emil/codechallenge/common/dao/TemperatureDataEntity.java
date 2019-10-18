package com.emil.codechallenge.common.dao;

import static com.emil.codechallenge.common.dao.TemperatureDataEntity.COLLECTION;

import com.emil.codechallenge.common.data.GPSCoordinates;
import com.emil.codechallenge.common.data.temperature.TemperatureData;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = COLLECTION)
public class TemperatureDataEntity {

  public static final String COLLECTION = "temperature_data";

  @Id
  private ObjectId id;
  private double temperature;
  private GPSCoordinates coordinates;
  private Instant captureTime;

  public static TemperatureDataEntity from(TemperatureData temperatureData) {
    val id = (temperatureData.getId() == null ? new ObjectId()
        : new ObjectId(temperatureData.getId()));
    return new TemperatureDataEntity(
        id,
        temperatureData.getTemperature(),
        temperatureData.getCoordinates(),
        temperatureData.getCaptureTime()
    );
  }

  public TemperatureData toCommon() {
    return TemperatureData.builder()
        .id(id.toString())
        .temperature(temperature)
        .coordinates(coordinates)
        .captureTime(captureTime)
        .build();
  }
}
