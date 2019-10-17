package com.emil.codechallenge.common.model.temp;

import com.emil.codechallenge.common.model.commons.GPSCoordinates;
import java.time.Instant;
import lombok.Builder;

@Builder
public class TemperatureData {

  private float temperature;
  private GPSCoordinates coordinates;
  private Instant captureTime;
}
