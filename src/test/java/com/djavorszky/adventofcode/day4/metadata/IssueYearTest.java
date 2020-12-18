package com.djavorszky.adventofcode.day4.metadata;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class IssueYearTest {

  @ParameterizedTest
  @ValueSource(strings = {"2010", "2014", "2020"})
  void testIssueYearValid(String input) {
    IssueYear issueYear = IssueYear.valueOf(input);

    assertThat(issueYear.isValid()).isTrue();
  }

  @ParameterizedTest
  @ValueSource(strings = {"2009", "2021", "30", "2402"})
  void testIssueYearInvalid(String input) {
    IssueYear issueYear = IssueYear.valueOf(input);

    assertThat(issueYear.isValid()).isFalse();
  }
}
