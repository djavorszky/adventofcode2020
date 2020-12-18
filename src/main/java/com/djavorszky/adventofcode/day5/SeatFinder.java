package com.djavorszky.adventofcode.day5;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SeatFinder {

  private static final char LOWER_ROW = 'F';
  private static final char UPPER_ROW = 'B';
  private static final char LOWER_COL = 'L';
  private static final char UPPER_COL = 'R';

  public static long findSeatId(String input) {
    return findRow(input.substring(0, 7)) * 8 + findSeat(input.substring(7));
  }

  private static int findRow(String inputPart) {
    int max = 128;
    int mid = 0;
    int min = 0;

    for (char c : inputPart.toCharArray()) {
      int diff = (max - min) / 2;
      if (c == LOWER_ROW) {
        max -= diff;
        mid = min + (max - min) / 2;
      } else if (c == UPPER_ROW) {
        min += diff;
        mid = min + (max - min) / 2;
      }
    }

    return mid;
  }

  private static int findSeat(String inputPart) {
    int max = 8;
    int mid = 0;
    int min = 0;

    for (char c : inputPart.toCharArray()) {
      int diff = (max - min) / 2;
      if (c == LOWER_COL) {
        max -= diff;
        mid = min + (max - min) / 2;
      } else if (c == UPPER_COL) {
        min += diff;
        mid = min + (max - min) / 2;
      }
    }

    return mid;
  }
}
