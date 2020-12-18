package com.djavorszky.adventofcode.day4.metadata;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class EyeColourTest {

  @ParameterizedTest
  @ValueSource(strings = {"amb", "blu", "brn", "grn", "gry", "hzl", "oth"})
  void testEyeColourIsValid(String input) {
    EyeColour eyeColour = EyeColour.valueOf(input);

    assertThat(eyeColour.isValid()).isTrue();
  }

  @ParameterizedTest
  @ValueSource(strings = {"green", "", "bluu", "sparkly"})
  void testEyeColourIsInvalid(String input) {
    EyeColour eyeColour = EyeColour.valueOf(input);

    assertThat(eyeColour.isValid()).isFalse();
  }
}
