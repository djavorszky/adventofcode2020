package com.djavorszky.adventofcode.day5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SeatFinderTest {

  @ParameterizedTest
  @CsvSource({"FBFBBFFRLR,357", "BFFFBBFRRR,567", "FFFBBBFRRR,119", "BBFFBBFRLL,820"})
  void seatIdIsReturned(String input, long expected) {
    assertThat(SeatFinder.findSeatId(input)).isEqualTo(expected);
  }
}
