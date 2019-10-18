package com.emil.codechallenge.common.data.temperature;

import com.emil.codechallenge.common.data.GPSCoordinates;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemperatureData {

  private String id;
  private double temperature;
  private GPSCoordinates coordinates;
  private Instant captureTime;
}
