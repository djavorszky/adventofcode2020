package com.djavorszky.adventofcode.day11

import com.djavorszky.adventofcode.helper.str.StringInputSource
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day11Test {

    private val day = Day11()

    @Test
    fun `task 1`() {
        val input = """L.LL.LL.LL
LLLLLLL.LL
L.L.L..L..
LLLL.LL.LL
L.LL.LL.LL
L.LLLLL.LL
..L.L.....
LLLLLLLLLL
L.LLLLLL.L
L.LLLLL.LL
"""

        assertThat(day.task1(StringInputSource(input, "\n"))).isEqualTo(37)
    }
}