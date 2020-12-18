package com.djavorszky.adventofcode;

import com.djavorszky.adventofcode.day1.Day1;
import com.djavorszky.adventofcode.day2.Day2;
import com.djavorszky.adventofcode.day3.Day3;
import com.djavorszky.adventofcode.helper.file.FileInputSource;
import com.djavorszky.adventofcode.util.Console;

import java.util.Map;

public class AdventOfCode {

    private static final Map<String, Task> tasks = Map.of(
            "day1/input.txt", new Day1(),
            "day2/input.txt", new Day2(),
            "day3/input.txt", new Day3());

    public static void main(String[] args) {
        tasks.forEach((filePath, task) -> {
            FileInputSource fileInputSource = null;
            try {
                fileInputSource = new FileInputSource(filePath);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }

            String dayName = task.getClass().getSimpleName();

            Console.print("========= %s =========", dayName);

            Console.print("Task 1: %s", task.task1(fileInputSource));
            Console.print("Task 2: %s", task.task2(fileInputSource));
        });

    }

}
