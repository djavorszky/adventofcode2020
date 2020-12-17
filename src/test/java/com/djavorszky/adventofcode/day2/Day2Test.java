package com.djavorszky.adventofcode.day2;

import com.djavorszky.adventofcode.helper.str.StringInputSource;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day2Test {

    private final Day2 day2 = new Day2();

    @Test
    void test_task1() {
        var testInput = "1-3 a: abcde\n1-3 b: cdefg\n2-9 c: ccccccccc\n";

        String result = day2.task1(new StringInputSource(testInput, "\n"));

        assertThat(result).isEqualTo("2");
    }


    @Test
    void test_task2() {
        var testInput = "1-3 a: abcde\n1-3 b: cdefg\n2-9 c: ccccccccc\n";

        String result = day2.task2(new StringInputSource(testInput, "\n"));

        assertThat(result).isEqualTo("1");
    }

}