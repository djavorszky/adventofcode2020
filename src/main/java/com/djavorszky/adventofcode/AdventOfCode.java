package com.djavorszky.adventofcode;

import com.djavorszky.adventofcode.day1.Day1;
import com.djavorszky.adventofcode.day2.Day2;
import com.djavorszky.adventofcode.day3.Day3;
import com.djavorszky.adventofcode.day4.Day4;
import com.djavorszky.adventofcode.day5.Day5;
import com.djavorszky.adventofcode.day6.Day6;
import com.djavorszky.adventofcode.day7.Day7;
import com.djavorszky.adventofcode.helper.file.FileInputSource;
import com.djavorszky.adventofcode.util.Console;

import java.util.Map;
import java.util.TreeMap;

public class AdventOfCode {

  private static final Map<String, Day> DAYS =
      new TreeMap<>() {
        {
          put("day1/input.txt", new Day1());
          put("day2/input.txt", new Day2());
          put("day3/input.txt", new Day3());
          put("day4/input.txt", new Day4());
          put("day5/input.txt", new Day5());
          put("day6/input.txt", new Day6());
          put("day7/input.txt", new Day7());
        }
      };

  public static void main(String[] args) {
    DAYS.forEach(
        (filePath, day) -> {
          FileInputSource fileInputSource = null;
          try {
            fileInputSource = new FileInputSource(filePath);
          } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
          }

          String dayName = day.getClass().getSimpleName();

          Console.print("========= %s =========", dayName);

          Console.print("Task 1: %s", day.task1(fileInputSource));
          Console.print("Task 2: %s", day.task2(fileInputSource));
        });
  }
}
