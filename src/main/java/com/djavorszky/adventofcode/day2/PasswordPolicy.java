package com.djavorszky.adventofcode.day2;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordPolicy {

  private final int min;
  private final int max;
  private final char letter;

  public static PasswordPolicy from(String input) {
    var inputParts = input.split(" ");

    var minMax = inputParts[0].split("-");

    return new PasswordPolicy(
        Integer.parseInt(minMax[0]), Integer.parseInt(minMax[1]), inputParts[1].charAt(0));
  }

  public boolean isValidV1(String password) {
    long foundLetters = password.chars().filter(c -> c == letter).count();

    return foundLetters >= min && foundLetters <= max;
  }

  public boolean isValidV2(String password) {
    boolean first = password.charAt(min - 1) == letter;
    boolean second = password.charAt(max - 1) == letter;

    return (first && !second) || (!first && second);
  }
}
