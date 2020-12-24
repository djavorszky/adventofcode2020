package com.djavorszky.adventofcode.day1;

import com.djavorszky.adventofcode.Day;
import com.djavorszky.adventofcode.helper.InputSource;

import java.util.Set;
import java.util.stream.Collectors;

public class Day1 implements Day {

  @Override
  public long task1(InputSource inputSource) {
    Set<Integer> numbers =
        inputSource.getInput().parallelStream().map(Integer::parseInt).collect(Collectors.toSet());

    long number =
        numbers.stream().filter(num -> numbers.contains(2020 - num)).findFirst().orElseThrow();
    long complement = 2020 - number;

    return number * complement;
  }

  @Override
  public long task2(InputSource inputSource) {
    Set<Integer> numbers =
        inputSource.getInput().parallelStream().map(Integer::parseInt).collect(Collectors.toSet());

    long result = 0;
    for (int num1 : numbers) {
      for (int num2 : numbers) {
        int num3 = 2020 - num1 - num2;
        if (numbers.contains(num3)) {
          result = num1 * num2 * num3;
          break;
        }
      }
    }

    return result;
  }
}
