package com.djavorszky.adventofcode.day2;

import com.djavorszky.adventofcode.Day;
import com.djavorszky.adventofcode.helper.InputSource;

public class Day2 implements Day {
    @Override
    public Long task1(InputSource inputSource) {
        return inputSource.getInput().parallelStream().filter(line -> {
            String[] parts = line.split(": ");

            PasswordPolicy pwp = PasswordPolicy.from(parts[0]);

            return pwp.isValidV1(parts[1]);
        }).count();
    }

    @Override
    public Long task2(InputSource inputSource) {
        return inputSource.getInput().parallelStream().filter(line -> {
            String[] parts = line.split(": ");

            PasswordPolicy pwp = PasswordPolicy.from(parts[0]);

            return pwp.isValidV2(parts[1]);
        }).count();
    }
}
