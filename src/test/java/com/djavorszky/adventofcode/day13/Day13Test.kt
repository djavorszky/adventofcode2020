package com.djavorszky.adventofcode.day13

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day13Test {

    private val day13 = Day13()

    @Test
    fun `task 1`() {
        val input = "939\n7,13,x,x,59,x,31,19"
        
        assertThat(day13.task1 { input.split("\n") }).isEqualTo(295)
    }
}