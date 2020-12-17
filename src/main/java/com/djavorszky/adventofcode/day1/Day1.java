package com.djavorszky.adventofcode.day1;

import com.djavorszky.adventofcode.Task;
import com.djavorszky.adventofcode.helper.InputSource;

import java.util.Set;
import java.util.stream.Collectors;

public class Day1 implements Task {

    @Override
    public String task1(InputSource inputSource) {
        Set<Integer> numbers = inputSource.getInput().parallelStream().map(Integer::parseInt).collect(Collectors.toSet());

        int number = numbers.stream().filter(num -> numbers.contains(2020 - num)).findFirst().orElseThrow();
        int complement = 2020 - number;

        return String.valueOf(number * complement);
    }

    @Override
    public String task2(InputSource inputSource) {
        Set<Integer> numbers = inputSource.getInput().parallelStream().map(Integer::parseInt).collect(Collectors.toSet());

        int result = 0;
        for (int num1 : numbers) {
            for (int num2 : numbers) {
                int num3 = 2020 - num1 - num2;
                if (numbers.contains(num3)) {
                    result = num1 * num2 * num3;
                    break;
                }

            }
        }

        return String.valueOf(result);
    }
}
