package com.djavorszky.adventofcode;

import com.djavorszky.adventofcode.day1.Day1Task1;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Main {

    private static final List<Task> tasks = List.of(new Day1Task1());

    public static void main(String[] args) {

        tasks.parallelStream().forEach(task -> {




        });

    }

}
