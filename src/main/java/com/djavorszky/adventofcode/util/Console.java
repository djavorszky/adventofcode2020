package com.djavorszky.adventofcode.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Console {

  public static void print(String msg) {
    System.out.println(msg);
  }

  public static void print(String msg, Object... args) {
    print(String.format(msg, args));
  }
}
