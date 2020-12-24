package com.djavorszky.adventofcode

import com.djavorszky.adventofcode.day1.Day1
import com.djavorszky.adventofcode.day10.Day10
import com.djavorszky.adventofcode.day11.Day11
import com.djavorszky.adventofcode.day12.Day12
import com.djavorszky.adventofcode.day2.Day2
import com.djavorszky.adventofcode.day3.Day3
import com.djavorszky.adventofcode.day4.Day4
import com.djavorszky.adventofcode.day5.Day5
import com.djavorszky.adventofcode.day6.Day6
import com.djavorszky.adventofcode.day7.Day7
import com.djavorszky.adventofcode.day8.Day8
import com.djavorszky.adventofcode.day9.Day9
import com.djavorszky.adventofcode.helper.file.FileInputSource

object AdventOfCode {
    private val DAYS = listOf(
            Day1(), Day2(), Day3(), Day4(),
            Day5(), Day6(), Day7(), Day8(),
            Day9(), Day10(), Day11(), Day12())

    @JvmStatic
    fun main(args: Array<String>) {
        DAYS.forEachIndexed { index, day ->
            val fileInputSource = FileInputSource("day${index + 1}/input.txt")

            println("========= ${day.javaClass.simpleName} =========")
            println("Task 1: ${day.task1(fileInputSource)}")
            println("Task 2: ${day.task2(fileInputSource)}")
        }

    }
}