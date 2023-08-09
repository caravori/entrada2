package nl.sidn.entrada2.worker.metric;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AvgMetric extends SumMetric {

  public AvgMetric(String name, int value, long time) {
    super(name, value, time);
  }

  @Override
  public double getValue() {

    if (samples > 0) {
      return value / samples;
    }
    return 0;
  }

}
