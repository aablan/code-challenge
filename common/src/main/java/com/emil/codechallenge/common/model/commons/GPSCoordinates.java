/*
 * Copyright (C) Siemens AG 2019 ALL RIGHTS RESERVED.
 */

package com.emil.codechallenge.common.model.commons;

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
