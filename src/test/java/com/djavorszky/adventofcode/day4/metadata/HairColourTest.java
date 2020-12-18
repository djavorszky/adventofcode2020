package com.djavorszky.adventofcode.day4.metadata;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class HairColourTest {

  @ParameterizedTest
  @ValueSource(strings = {"#123456", "#7890ab", "#cdef12"})
  void testHairColourValid(String input) {
    HairColour hairColour = HairColour.valueOf(input);

    assertThat(hairColour.isValid()).isTrue();
  }

  @ParameterizedTest
  @ValueSource(strings = {"#1234561", "#7890", "#nono", "stillnothing", "1234567", "123456"})
  void testHairColourInvalid(String input) {
    HairColour hairColour = HairColour.valueOf(input);

    assertThat(hairColour.isValid()).isFalse();
  }
}
