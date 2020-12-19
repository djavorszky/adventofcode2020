package com.djavorszky.adventofcode.day7;

public record Bags(int amount, String name) {
  public static Bags construct(String input) {
    input = input.trim();

    return new Bags(
            Integer.parseInt(input.substring(0, 1)),
            input.substring(2, input.indexOf("bag")).trim());
  }


}
