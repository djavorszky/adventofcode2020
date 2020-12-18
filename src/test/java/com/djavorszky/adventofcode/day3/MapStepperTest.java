package com.djavorszky.adventofcode.day3;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class MapStepperTest {


    @Test
    void testMapStepper() {
        String testInput = "..##.......\n" +
                "#...#...#..\n" +
                ".#....#..#.\n" +
                "..#.#...#.#\n" +
                ".#...##..#.\n" +
                "..#.##.....\n" +
                ".#.#.#....#\n" +
                ".#........#\n" +
                "#.##...#...\n" +
                "#...##....#\n" +
                ".#..#...#.#\n";

        MapStepper mapStepper = new MapStepper(3, 1, Arrays.asList(testInput.split("\n")));

        assertThat(mapStepper.countTrees()).isEqualTo(7);
    }
}