package com.djavorszky.adventofcode.day4;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MultiLineCondenserTest {

  @Test
  void testCondense() {
    List<String> input = List.of("abba", "b", "c", "", "d", "e", "", "f", "g");
    List<String> expected = List.of("abba b c", "d e", "f g");

    List<String> condensedInput = MultiLineCondenser.condense(input);

    assertThat(condensedInput).isEqualTo(expected);
  }
}
