package com.djavorszky.adventofcode.day2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordPolicyTest {

  @Test
  void testPasswordPolicyCreation() {
    PasswordPolicy pwp = PasswordPolicy.from("1-3 a");

    assertThat(pwp).isNotNull();
    assertThat(pwp.getMax()).isEqualTo(3);
    assertThat(pwp.getMin()).isEqualTo(1);
    assertThat(pwp.getLetter()).isEqualTo('a');
  }

  @Test
  void testPasswordPolicyIsValidV1() {
    PasswordPolicy pwp = PasswordPolicy.from("1-3 a");

    assertThat(pwp.isValidV1("abcde")).isTrue();

    PasswordPolicy pwp2 = PasswordPolicy.from("2-9 c");

    assertThat(pwp2.isValidV1("ccccccccc")).isTrue();
  }

  @Test
  void testPasswordPolicyIsInvalidV1() {
    PasswordPolicy pwp = PasswordPolicy.from("1-3 b");

    assertThat(pwp.isValidV1("cdefg")).isFalse();
  }

  @Test
  void testPasswordPolicyIsValidV2() {
    PasswordPolicy pwp = PasswordPolicy.from("1-3 a");

    assertThat(pwp.isValidV2("abcde")).isTrue();
  }

  @Test
  void testPasswordPolicyIsInvalidV2() {
    PasswordPolicy pwp2 = PasswordPolicy.from("2-9 c");

    assertThat(pwp2.isValidV2("ccccccccc")).isFalse();

    PasswordPolicy pwp = PasswordPolicy.from("1-3 b");

    assertThat(pwp.isValidV2("cdefg")).isFalse();
  }
}
