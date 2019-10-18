package com.emil.codechallenge.web.api;

import com.emil.codechallenge.common.data.temperature.TemperatureData;
import com.emil.codechallenge.web.service.TemperatureDataService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller that provides http access over temperature data resource
 *
 * @see TemperatureData
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/temperature/1.0")
public class TemperatureDataController {

  private final TemperatureDataService temperatureDataService;

  @GetMapping
  public List<TemperatureData> temperatureData() {
    return temperatureDataService.temperatureData();
  }
}
