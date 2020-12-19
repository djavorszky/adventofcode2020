package com.djavorszky.adventofcode.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class InputParserTest {

  @Test
  void testCondenseSingleLine() {
    List<String> input = List.of("abba", "b", "c", "", "d", "e", "", "f", "g");
    List<String> expected = List.of("abba b c", "d e", "f g");

    List<String> condensedInput = InputParser.parseToInputLines(input);

    assertThat(condensedInput).isEqualTo(expected);
  }

  @Test
  void testCondenseMultiLine() {
    List<String> input = List.of("abba", "b", "c", "", "d", "e", "", "f", "g");
    List<List<String>> expected =
        List.of(List.of("abba", "b", "c"), List.of("d", "e"), List.of("f", "g"));

    assertThat(InputParser.parseToInputSections(input)).isEqualTo(expected);
  }
}
