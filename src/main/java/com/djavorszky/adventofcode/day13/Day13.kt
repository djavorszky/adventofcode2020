package com.djavorszky.adventofcode.day13

import com.djavorszky.adventofcode.Day
import com.djavorszky.adventofcode.helper.InputSource
import java.util.stream.Collectors

class Day13 : Day {
    override fun task1(inputSource: InputSource): Long {
        val startTime = inputSource.input.first().toInt()

        val earliestBus = inputSource.input.last()
                .split(",")
                .parallelStream()
                .filter { it != "x" }
                .map { Bus(it.toInt()) }
                .collect(Collectors.toList())
                .minByOrNull { it.firstDepartureAfter(startTime) }
                ?: throw IllegalArgumentException("no such bus found")

        val waitTime = earliestBus.firstDepartureAfter(startTime) - startTime

        return (earliestBus.id * waitTime).toLong()
    }

    override fun task2(inputSource: InputSource): Long {
        TODO("Not yet implemented")
    }
}