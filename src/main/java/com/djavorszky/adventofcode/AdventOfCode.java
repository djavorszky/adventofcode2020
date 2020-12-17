package com.djavorszky.adventofcode;

import com.djavorszky.adventofcode.day1.Day1;
import com.djavorszky.adventofcode.helper.file.FileInputSource;
import com.djavorszky.adventofcode.util.Console;

import java.util.Map;

public class AdventOfCode {

    private static final Map<String, Task> tasks = Map.of(
            "day1/input.txt", new Day1());

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

            Console.print("%s task 1: %s", dayName, task.task1(fileInputSource));
            Console.print("%s task 2: %s", dayName, task.task2(fileInputSource));

            Console.print("=======================");

        });

    }

}
