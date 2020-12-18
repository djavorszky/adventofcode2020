package com.djavorszky.adventofcode.day1;

import com.djavorszky.adventofcode.helper.str.StringInputSource;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day1Test {

  private final Day1 day1 = new Day1();

  @Test
  void task1_test() {
    var testInput = "1721,979,366,299,675,1456";

    var result = day1.task1(new StringInputSource(testInput));

    assertThat(result).isEqualTo(514579);
  }

  @Test
  void task2_test() {
    var testInput = "1721,979,366,299,675,1456";

    var result = day1.task2(new StringInputSource(testInput));

    assertThat(result).isEqualTo(241861950);
  }
}
