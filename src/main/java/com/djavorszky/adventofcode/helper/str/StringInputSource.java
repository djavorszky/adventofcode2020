package com.djavorszky.adventofcode.helper.str;

import com.djavorszky.adventofcode.helper.InputSource;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class StringInputSource implements InputSource {

  private final List<String> input;

  public StringInputSource(String inputString) {
    this(inputString, ",");
  }

  public StringInputSource(String inputString, String delimiter) {
    input = Arrays.asList(inputString.split(delimiter));
  }

  @Override
  public List<String> getInput() {
    return input;
  }
}
