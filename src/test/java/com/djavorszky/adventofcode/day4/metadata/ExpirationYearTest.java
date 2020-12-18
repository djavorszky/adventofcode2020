package com.djavorszky.adventofcode.day4.metadata;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class ExpirationYearTest {

  @ParameterizedTest
  @ValueSource(strings = {"2020", "2024", "2030"})
  void testExpirationYearValid(String input) {
    ExpirationYear expirationYear = ExpirationYear.valueOf(input);

    assertThat(expirationYear.isValid()).isTrue();
  }

  @ParameterizedTest
  @ValueSource(strings = {"2031", "2019", "2040", "2000"})
  void testExpirationYearInvalid(String input) {
    ExpirationYear expirationYear = ExpirationYear.valueOf(input);

    assertThat(expirationYear.isValid()).isFalse();
  }
}
