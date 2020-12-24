package com.djavorszky.adventofcode

import com.djavorszky.adventofcode.helper.InputSource

interface Day {
    fun task1(inputSource: InputSource): Long
    fun task2(inputSource: InputSource): Long
}