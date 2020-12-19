package com.djavorszky.adventofcode.day6;

import com.djavorszky.adventofcode.helper.str.StringInputSource;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day6Test {

  private final Day6 day6 = new Day6();

  @Test
  void testTask1() {
    StringInputSource stringInputSource = new StringInputSource("""
            abc

            a
            b
            c

            ab
            ac

            a
            a
            a
            a

            b""", "\n");

    assertThat(day6.task1(stringInputSource)).isEqualTo(11);
  }



  @Test
  void testTask2() {
    StringInputSource stringInputSource = new StringInputSource("""
            abc

            a
            b
            c

            ab
            ac

            a
            a
            a
            a

            b""", "\n");

    assertThat(day6.task2(stringInputSource)).isEqualTo(6);
  }
}
