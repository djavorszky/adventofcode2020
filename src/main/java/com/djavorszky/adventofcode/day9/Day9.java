package com.djavorszky.adventofcode.day9;

import com.djavorszky.adventofcode.Day;
import com.djavorszky.adventofcode.helper.InputSource;

import java.util.stream.Collectors;

public class Day9 implements Day {
  @Override
  public Long task1(InputSource inputSource) {
    CodeDecoder codeDecoder = new CodeDecoder();

    return codeDecoder.findTask1Weakness(
        25, inputSource.getInput().stream().map(Long::parseLong).collect(Collectors.toList()));
  }

  @Override
  public Long task2(InputSource inputSource) {
    CodeDecoder codeDecoder = new CodeDecoder();

    return codeDecoder.findTask2Weakness(
        25, inputSource.getInput().stream().map(Long::parseLong).collect(Collectors.toList()));
  }
}
