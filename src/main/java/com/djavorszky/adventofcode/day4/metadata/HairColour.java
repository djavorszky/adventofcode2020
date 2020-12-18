package com.djavorszky.adventofcode.day4.metadata;

import com.djavorszky.adventofcode.day4.PassportField;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class HairColour implements PassportField {
  private final String value;

  private final boolean valid;

  public static HairColour valueOf(String input) {

    boolean valid = false;
    if (input.startsWith("#") && input.length() == 7) {
      try {
        Integer.parseInt(input.substring(1), 16);
        valid = true;
      } catch (NumberFormatException e) {
        // nothing to do
      }
    }

    return new HairColour(input, valid);
  }
}
