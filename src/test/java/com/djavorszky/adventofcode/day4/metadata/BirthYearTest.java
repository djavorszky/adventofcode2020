package com.djavorszky.adventofcode.day4.metadata;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class BirthYearTest {

  @ParameterizedTest
  @ValueSource(strings = {"1920", "2000", "2002"})
  void testBirthYearValid(String input) {
    BirthYear birthYear = BirthYear.valueOf(input);

    assertThat(birthYear.isValid()).isTrue();
  }

  @ParameterizedTest
  @ValueSource(strings = {"1919", "1000", "2003", "3600"})
  void testBirthYearInvalid(String input) {
    BirthYear birthYear = BirthYear.valueOf(input);

    assertThat(birthYear.isValid()).isFalse();
  }
}
