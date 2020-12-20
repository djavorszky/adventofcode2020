package com.djavorszky.adventofcode.day9;

import java.util.List;

public class CodeDecoder {

  public long findTask1Weakness(int preambleSize, List<Long> numbers) {
    mainLoop:
    for (int i = preambleSize; i < numbers.size(); i++) {
      long currentNumber = numbers.get(i);

      int start = i - preambleSize;
      for (int j = start; j < i; j++) {
        int secondStart = j + 1;
        for (int k = secondStart; k < i; k++) {
          if (numbers.get(j) + numbers.get(k) == currentNumber) {
            continue mainLoop;
          }
        }
      }

      return currentNumber;
    }
    return 0;
  }

  public long findTask2Weakness(int preambleSize, List<Long> numbers) {
    long vulnerableNumber = findTask1Weakness(preambleSize, numbers);

    mainLoop:
    for (int i = 0; i < numbers.size() - 1; i++) {
      long sum = numbers.get(i);

      for (int j = i + 1; j < numbers.size(); j++) {
        sum += numbers.get(j);

        if (sum == vulnerableNumber) {
          MinMax minMax = getMinMaxInRange(numbers.subList(i, j + 1));
          return minMax.min + minMax.max;
        } else if (sum > vulnerableNumber) {
          continue mainLoop;
        }
      }
    }

    return 0;
  }

  private MinMax getMinMaxInRange(List<Long> subList) {
    return new MinMax(
        subList.stream().max(Long::compare).get(), subList.stream().min(Long::compare).get());
  }

  record MinMax(long min, long max) {}
}
