package com.djavorszky.adventofcode.day4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class PassportTest {

  @ParameterizedTest
  @ValueSource(
      strings = {
        "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm",
        "hcl:#ae17e1 iyr:2013 eyr:2024 ecl:brn pid:760753108 byr:1931 hgt:179cm"
      })
  void testValidPassportsV1(String input) {
    Passport passport = Passport.valueOf(input);

    assertThat(passport.isValidV1()).isTrue();
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 hcl:#cfa07d byr:1929",
        "hcl:#cfa07d eyr:2025 pid:166559648 iyr:2011 ecl:brn hgt:59in"
      })
  void testInValidPassportsV1(String input) {
    Passport passport = Passport.valueOf(input);

    assertThat(passport.isValidV1()).isFalse();
  }
}
