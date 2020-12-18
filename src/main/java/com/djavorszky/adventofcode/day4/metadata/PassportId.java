package com.djavorszky.adventofcode.day4.metadata;

import com.djavorszky.adventofcode.day4.PassportField;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PassportId implements PassportField {
  private final String value;

  private final boolean valid;

  public static PassportId valueOf(String input) {
    boolean valid = false;

    if (input.length() == 9) {
      try {
        Integer.parseInt(input);
        valid = true;
      } catch (NumberFormatException e) {
        // nothing to do
      }
    }

    return new PassportId(input, valid);
  }
}
