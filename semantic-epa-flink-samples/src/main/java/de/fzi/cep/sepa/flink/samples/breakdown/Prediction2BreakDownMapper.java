package de.fzi.cep.sepa.flink.samples.breakdown;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

import java.util.Map;

/**
 * Created by riemer on 12.02.2017.
 */
public class Prediction2BreakDownMapper implements FlatMapFunction<Map<String, Object>, Map<String, Object>>  {


  @Override
  public void flatMap(Map<String, Object> stringObjectMap, Collector<Map<String, Object>> collector) throws Exception {
    collector.collect(stringObjectMap);
  }
}
