package com.emil.codechallenge.dataprocessor.service.persister.temp;

import com.emil.codechallenge.common.model.temp.TemperatureData;

public interface TemperatureDataPersister {

  void save(final TemperatureData data);
}
