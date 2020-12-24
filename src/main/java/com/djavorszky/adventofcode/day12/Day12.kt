package com.djavorszky.adventofcode.day12

import com.djavorszky.adventofcode.Day
import com.djavorszky.adventofcode.helper.InputSource

class Day12 : Day {

    private val directions = mapOf(
            'N' to Direction.NORTH,
            'E' to Direction.EAST,
            'S' to Direction.SOUTH,
            'W' to Direction.WEST
    )

    private val turns = mapOf(
            'L' to TurnDirection.LEFT,
            'R' to TurnDirection.RIGHT
    )

    override fun task1(inputSource: InputSource): Long {
        val ship = Ship()

        inputSource.input.forEach {
            val instruction = it[0]

            val amount = it.substring(1 until it.length).toInt()

            if (instruction == 'F') {
                ship.moveForward(amount)
            }

            directions[instruction]?.let { dir -> ship.move(dir, amount) }

            turns[instruction]?.let { dir -> ship.turn(dir, amount) }
        }

        return ship.getDistance()
    }

    override fun task2(inputSource: InputSource): Long {

        val ship = Ship()

        inputSource.input.forEach {
            val instruction = it[0]

            val amount = it.substring(1 until it.length).toInt()

            if (instruction == 'F') {
                ship.moveToWaypoint(amount)
            }

            directions[instruction]?.let { dir -> ship.moveWaypoint(dir, amount) }

            turns[instruction]?.let { dir -> ship.rotateWaypoint(dir, amount) }
        }

        return ship.getDistance()
    }
}