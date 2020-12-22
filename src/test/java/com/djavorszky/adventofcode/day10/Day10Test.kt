package com.djavorszky.adventofcode.day10

import com.djavorszky.adventofcode.Day
import com.djavorszky.adventofcode.helper.str.StringInputSource
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day10Test {

    private val day10: Day = Day10();

    @Test
    fun `task 1 with large input`() {
        val testInput = "28,33,18,42,31,14,46,20,48,47,24,23,49,45,19,38,39,11,1,32,25,35,8,17,7,9,4,2,34,10,3"

        val result = day10.task1(StringInputSource(testInput))

        assertThat(result).isEqualTo(220)
    }

    @Test
    fun `task 1 with small input`() {
        val testInput = "16,10,15,5,1,11,7,19,6,12,4"

        val result = day10.task1(StringInputSource(testInput))

        assertThat(result).isEqualTo(35)
    }
}