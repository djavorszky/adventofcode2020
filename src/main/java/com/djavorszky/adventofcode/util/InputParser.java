package com.djavorszky.adventofcode.util;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class InputParser {

  public static List<String> parseToInputLines(List<String> lines) {
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

  public static List<List<String>> parseToInputSections(List<String> lines) {
    List<List<String>> result = new ArrayList<>();

    List<String> section = new ArrayList<>();
    for (String line : lines) {
      if (line.equals("")) {
        result.add(section);
        section = new ArrayList<>();

        continue;
      }

      section.add(line);
    }

    if (!section.isEmpty()) {
      result.add(section);
    }

    return result;
  }
}
