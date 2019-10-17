package com.emil.codechallenge.dataprocessor.service.persister.temperature;

import com.emil.codechallenge.common.model.temp.TemperatureData;

/**
 * Base interface for all temperature data persisters
 */
public interface TemperatureDataPersister {

  /**
   * This method will save the passed data object to some DB vendor i.e depends on the implementing
   * class
   */
  void save(final TemperatureData data);
}
