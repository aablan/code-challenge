package com.emil.codechallenge.web.repository;

import com.emil.codechallenge.common.data.temperature.TemperatureData;
import java.util.List;

/**
 * Abstraction layer class to provide general repository functionality that is decoupled from the db
 * vendor (mysql, mongo, ..)
 */
public interface TemperatureDataRepository {

  List<TemperatureData> allTemperatureData();
}
