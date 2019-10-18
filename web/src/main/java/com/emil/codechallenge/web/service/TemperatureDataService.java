package com.emil.codechallenge.web.service;

import com.emil.codechallenge.common.data.temperature.TemperatureData;
import com.emil.codechallenge.web.repository.TemperatureDataRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * General service class that operates on the temperature data as a resource
 */
@RequiredArgsConstructor
@Service
public class TemperatureDataService {

  private final TemperatureDataRepository temperatureDataRepository;

  public List<TemperatureData> temperatureData() {
    return temperatureDataRepository.allTemperatureData();
  }
}
