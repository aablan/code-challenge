package com.emil.codechallenge.common.model.temp;

import com.emil.codechallenge.common.model.commons.GPSCoordinates;
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

  private double temperature;
  private GPSCoordinates coordinates;
  private Instant captureTime;
}
