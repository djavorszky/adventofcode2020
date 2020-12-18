package com.djavorszky.adventofcode.day4.metadata;

import com.djavorszky.adventofcode.day4.PassportField;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class EyeColour implements PassportField {
  private static final Set<String> VALID_COLOURS =
      Set.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");

  private final String value;

  private final boolean valid;

  public static EyeColour valueOf(String input) {
    return new EyeColour(input, VALID_COLOURS.contains(input));
  }
}
