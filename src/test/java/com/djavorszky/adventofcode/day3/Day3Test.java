package com.djavorszky.adventofcode.day3;

import com.djavorszky.adventofcode.helper.str.StringInputSource;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day3Test {

  private final String testInput =
      "..##.......\n"
          + "#...#...#..\n"
          + ".#....#..#.\n"
          + "..#.#...#.#\n"
          + ".#...##..#.\n"
          + "..#.##.....\n"
          + ".#.#.#....#\n"
          + ".#........#\n"
          + "#.##...#...\n"
          + "#...##....#\n"
          + ".#..#...#.#\n";

  private final Day3 day3 = new Day3();

  @Test
  void testTask1() {
    Long result = day3.task1(new StringInputSource(testInput, "\n"));

    assertThat(result).isEqualTo(7);
  }

  @Test
  void testTask2() {
    Long result = day3.task2(new StringInputSource(testInput, "\n"));

    assertThat(result).isEqualTo(336);
  }
}
