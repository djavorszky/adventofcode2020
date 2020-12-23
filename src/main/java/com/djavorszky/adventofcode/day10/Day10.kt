package com.djavorszky.adventofcode.day10

import com.djavorszky.adventofcode.Day
import com.djavorszky.adventofcode.helper.InputSource

class Day10 : Day {
    override fun task1(inputSource: InputSource): Long {
        val sortedList = inputSource.input.map { it.toLong() }.sorted().toMutableList()

        sortedList.add(0, 0)

        var oneDiff: Long = 0
        var twoDiff: Long = 0
        var threeDiff: Long = 1
        for (idx in sortedList.slice(0 until sortedList.size - 1).indices) {
            val currentItem = sortedList[idx]
            val nextItem = sortedList[idx + 1]

            when (nextItem - currentItem) {
                1L -> oneDiff++
                2L -> twoDiff++
                3L -> threeDiff++
                else -> break
            }
        }

        return oneDiff * threeDiff
    }

    override fun task2(inputSource: InputSource): Long {
        return 0
    }
}