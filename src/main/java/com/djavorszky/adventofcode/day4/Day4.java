package com.djavorszky.adventofcode.day4;

import com.djavorszky.adventofcode.Day;
import com.djavorszky.adventofcode.helper.InputSource;
import com.djavorszky.adventofcode.util.InputParser;

import java.util.List;

public class Day4 implements Day {
  @Override
  public Long task1(InputSource inputSource) {
    List<String> condensedInput = InputParser.parseToInputLines(inputSource.getInput());

    return condensedInput.parallelStream()
        .map(Passport::valueOf)
        .filter(Passport::isValidV1)
        .count();
  }

  @Override
  public Long task2(InputSource inputSource) {
    List<String> condensedInput = InputParser.parseToInputLines(inputSource.getInput());

    return condensedInput.parallelStream()
        .map(Passport::valueOf)
        .filter(Passport::isValidV2)
        .count();
  }
}
