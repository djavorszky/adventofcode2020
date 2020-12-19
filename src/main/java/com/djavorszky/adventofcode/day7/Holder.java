package com.djavorszky.adventofcode.day7;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class Holder {
  private final String name;

  private final List<Bags> containedBags;

  // light red bags contain 1 bright white bag, 2 muted yellow bags.
  public static Holder from(String input) {
    String[] parts = input.split("contain");

    String name = parts[0].substring(0, parts[0].indexOf(" bag"));

    return new Holder(name, getContainedBags(parts[1]));
  }

  private static List<Bags> getContainedBags(String containsInput) {
    if (containsInput.contains("no other")) {
      return Collections.emptyList();
    }

    return Arrays.stream(containsInput.split(","))
        .map(Bags::construct)
        .collect(Collectors.toList());
  }
}
