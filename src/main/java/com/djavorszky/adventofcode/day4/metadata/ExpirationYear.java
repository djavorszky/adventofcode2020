package com.djavorszky.adventofcode.day4.metadata;

import com.djavorszky.adventofcode.day4.PassportField;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExpirationYear implements PassportField {
  private final int value;

  public static ExpirationYear valueOf(String input) {
    return new ExpirationYear(Integer.parseInt(input));
  }

  @Override
  public boolean isValid() {
    return value >= 2020 && value <= 2030;
  }
}
