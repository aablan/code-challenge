package com.emil.codechallenge.dataprocessor.repository.dao;

import static com.emil.codechallenge.dataprocessor.repository.dao.TemperatureDataEntity.COLLECTION;

import com.emil.codechallenge.common.model.commons.GPSCoordinates;
import com.emil.codechallenge.common.model.temp.TemperatureData;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    return new TemperatureDataEntity(
        new ObjectId(),
        temperatureData.getTemperature(),
        temperatureData.getCoordinates(),
        temperatureData.getCaptureTime()
    );
  }
}
