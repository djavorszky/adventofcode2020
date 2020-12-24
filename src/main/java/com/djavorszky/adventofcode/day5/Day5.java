package com.djavorszky.adventofcode.day5;

import com.djavorszky.adventofcode.Day;
import com.djavorszky.adventofcode.helper.InputSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 implements Day {
  @Override
  public long task1(InputSource inputSource) {
    return Collections.max(
        inputSource.getInput().parallelStream()
            .map(SeatFinder::findSeatId)
            .collect(Collectors.toList()));
  }

  @Override
  public long task2(InputSource inputSource) {

    List<Long> seatIds =
        inputSource.getInput().parallelStream()
            .map(SeatFinder::findSeatId)
            .sorted()
            .collect(Collectors.toList());

    for (int i = 1; i < seatIds.size(); i++) {
      long previous = seatIds.get(i - 1);
      long current = seatIds.get(i);

      if (current - previous != 1) {
        return previous + 1;
      }
    }

    return 0L;
  }
}
