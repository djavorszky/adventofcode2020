package com.djavorszky.adventofcode.day12

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day12Test {

    private val day12 = Day12()

    @Test
    fun `task 1`() {
        val input = "F10,N3,F7,R90,F11"

        assertThat(day12.task1 { input.split(",") }).isEqualTo(25)
    }

    @Test
    fun `move is applied correctly in task 1`() {
        val tests = mapOf(
                "N10,S10" to 0L,
                "N10" to 10L,
                "S10" to 10L,
                "S10,N10" to 0L,
                "W10" to 10L,
                "E10" to 10L,
                "W10,E10" to 0L,
                "N10,E10" to 20L,
                "N10,W10" to 20L,
                "S10,E10" to 20L,
                "S10,W10" to 20L,
                "S10,W10,E10,N10" to 0L,
        )

        val day12 = Day12()

        for ((input, expectedDistance) in tests) {
            assertThat(day12.task1 { input.split(",") }).`as`("failed for test $input").isEqualTo(expectedDistance)
        }
    }


    @Test
    fun `turn is applied correctly in task 1`() {
        // by turning to a different direction and moving 10 spaces there, the test then negates it by a normal move
        // and thus we're expecting to arrive at the same place we started at.
        val tests = mapOf(
                "L90,F10,S10" to 0L,
                "R90,F10,N10" to 0L,
                "L180,F10,E10" to 0L,
                "R180,F10,E10" to 0L,
                "L270,F10,N10" to 0L,
                "R270,F10,S10" to 0L,
        )

        val day12 = Day12()

        for ((input, expectedDistance) in tests) {
            assertThat(day12.task1 { input.split(",") }).`as`("failed for test $input").isEqualTo(expectedDistance)
        }
    }

    @Test
    fun `task 2`() {
        val input = "F10,N3,F7,R90,F11"

        assertThat(day12.task2 { input.split(",") }).isEqualTo(286)
    }
}
