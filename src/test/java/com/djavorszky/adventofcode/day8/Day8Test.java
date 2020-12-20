package com.djavorszky.adventofcode.day8;

import com.djavorszky.adventofcode.Day;
import com.djavorszky.adventofcode.helper.str.StringInputSource;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day8Test {

  private final Day day8 = new Day8();

  @Test
  void testTask1() {
    String input = """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            jmp -4
            acc +6
            """;

    assertThat(day8.task1(new StringInputSource(input, "\n"))).isEqualTo(5);
  }

  @Test
  void testTask2() {
    String input = """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            jmp -4
            acc +6
            """;

    assertThat(day8.task2(new StringInputSource(input, "\n"))).isEqualTo(8);
  }
}
