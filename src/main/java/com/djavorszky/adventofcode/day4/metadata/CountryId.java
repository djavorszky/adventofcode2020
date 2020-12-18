package com.djavorszky.adventofcode.day4.metadata;

import com.djavorszky.adventofcode.day4.PassportField;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CountryId implements PassportField {
  private final String value;

  @Override
  public boolean isValid() {
    return true;
  }
}
