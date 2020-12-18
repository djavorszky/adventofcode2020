package com.djavorszky.adventofcode.day4.metadata;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class HeightTest {

  @ParameterizedTest
  @ValueSource(strings = {"150cm", "180cm", "193cm", "59in", "65in", "76in"})
  void testHeightValid(String input) {
    Height height = Height.valueOf(input);

    assertThat(height.isValid()).isTrue();
  }

  @ParameterizedTest
  @ValueSource(strings = {"149cm", "194cm", "193", "58in", "65cn", "77in"})
  void testHeightInvalid(String input) {
    Height height = Height.valueOf(input);

    assertThat(height.isValid()).isFalse();
  }
}
