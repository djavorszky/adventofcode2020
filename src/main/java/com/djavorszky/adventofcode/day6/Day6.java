package com.djavorszky.adventofcode.day6;

import com.djavorszky.adventofcode.Day;
import com.djavorszky.adventofcode.helper.InputSource;
import com.djavorszky.adventofcode.util.InputParser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day6 implements Day {
  @Override
  public long task1(InputSource inputSource) {
    return InputParser.parseToInputLines(inputSource.getInput()).parallelStream()
        .map(
            line -> {
              Set<Character> chars = new HashSet<>();

              for (char c : line.toCharArray()) {
                if (c == ' ') continue;
                chars.add(c);
              }

              return chars.size();
            })
        .reduce(0, Integer::sum)
        .longValue();
  }

  @Override
  public long task2(InputSource inputSource) {
    return InputParser.parseToInputSections(inputSource.getInput()).parallelStream()
        .map(
            section -> {
              int numberOfPeople = section.size();

              Map<Character, Integer> charMap = new HashMap<>();

              section.forEach(
                  line -> {
                    for (char c : line.toCharArray()) {
                      if (c == ' ') continue;
                      charMap.compute(c, (k, v) -> v == null ? 1 : ++v);
                    }
                  });

              return charMap.values().stream().filter(v -> v == numberOfPeople).count();
            })
        .reduce(0L, Long::sum);
  }
}
