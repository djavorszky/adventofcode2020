package com.djavorszky.adventofcode.day4.metadata;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class PassportIdTest {

  @ParameterizedTest
  @ValueSource(strings = {"123456789", "000000001"})
  void testPassportIdValid(String input) {
    PassportId passportId = PassportId.valueOf(input);

    assertThat(passportId.isValid()).isTrue();
  }

  @ParameterizedTest
  @ValueSource(strings = {"123456789000", "1234567", "1234000ab"})
  void testPassportIdInvalid(String input) {
    PassportId passportId = PassportId.valueOf(input);

    assertThat(passportId.isValid()).isFalse();
  }
}
