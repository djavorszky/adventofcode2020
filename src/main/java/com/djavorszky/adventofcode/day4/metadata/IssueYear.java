package com.djavorszky.adventofcode.day4.metadata;

import com.djavorszky.adventofcode.day4.PassportField;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IssueYear implements PassportField {
  private final int value;

  public static IssueYear valueOf(String input) {
    return new IssueYear(Integer.parseInt(input));
  }

  @Override
  public boolean isValid() {
    return value >= 2010 && value <= 2020;
  }
}
