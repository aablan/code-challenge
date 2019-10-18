package com.emil.codechallenge.common.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GPSCoordinates {

  private float latitude;
  private float longitude;
}
