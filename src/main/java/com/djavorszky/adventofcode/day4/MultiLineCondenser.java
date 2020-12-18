package com.djavorszky.adventofcode.day4;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class MultiLineCondenser {

  public static List<String> condense(List<String> lines) {
    List<String> result = new ArrayList<>();

    StringBuilder sb = new StringBuilder();
    for (String line : lines) {
      if (line.equals("")) {
        result.add(sb.toString().trim());

        sb = new StringBuilder();
      }

      sb.append(" ");
      sb.append(line);
    }

    if (!sb.isEmpty()) {
      result.add(sb.toString().trim());
    }

    return result;
  }
}
