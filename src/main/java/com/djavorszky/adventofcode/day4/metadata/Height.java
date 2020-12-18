package com.djavorszky.adventofcode.day4.metadata;

import com.djavorszky.adventofcode.day4.PassportField;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Height implements PassportField {
  private final String value;
  private final boolean valid;

  public static Height valueOf(String input) {
    boolean valid = false;

    if (input.contains("cm")) {
      int height = Integer.parseInt(input.substring(0, input.indexOf("cm")));

      valid = height >= 150 && height <= 193;
    } else if (input.contains("in")) {
      int height = Integer.parseInt(input.substring(0, input.indexOf("in")));

      valid = height >= 59 && height <= 76;
    }

    return new Height(input, valid);
  }
}
