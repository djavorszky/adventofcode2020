package com.djavorszky.adventofcode.day3;

import com.djavorszky.adventofcode.Day;
import com.djavorszky.adventofcode.helper.InputSource;

import java.util.Arrays;
import java.util.List;

public class Day3 implements Day {
  @Override
  public long task1(InputSource inputSource) {
    List<String> input = inputSource.getInput();

    MapStepper mapStepper = new MapStepper(3, 1, input);

    return mapStepper.countTrees();
  }

  @Override
  public long task2(InputSource inputSource) {
    List<String> input = inputSource.getInput();

    List<MapStepper> mapSteppers =
        Arrays.asList(
            new MapStepper(1, 1, input),
            new MapStepper(3, 1, input),
            new MapStepper(5, 1, input),
            new MapStepper(7, 1, input),
            new MapStepper(1, 2, input));

    return mapSteppers.parallelStream().map(MapStepper::countTrees).reduce(1L, (a, b) -> a * b);
  }
}
