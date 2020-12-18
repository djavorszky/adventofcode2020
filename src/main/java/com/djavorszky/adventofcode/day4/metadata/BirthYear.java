package com.djavorszky.adventofcode.day4.metadata;

import com.djavorszky.adventofcode.day4.PassportField;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BirthYear implements PassportField {
  private final int value;

  public static BirthYear valueOf(String input) {
    return new BirthYear(Integer.parseInt(input));
  }

  @Override
  public boolean isValid() {
    return value >= 1920 && value <= 2002;
  }
}
