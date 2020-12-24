package com.djavorszky.adventofcode.day12

import kotlin.math.abs

class Ship {

    private var currentDirection = Direction.EAST
    private var xLocation = 0
    private var yLocation = 0

    fun getDistance(): Long {
        return (abs(xLocation) + abs(yLocation)).toLong()
    }

    fun move(direction: Direction, amount: Int) {
        when (direction) {
            Direction.NORTH -> yLocation += amount
            Direction.EAST -> xLocation += amount
            Direction.SOUTH -> yLocation -= amount
            Direction.WEST -> xLocation -= amount
        }
    }

    fun moveForward(amount: Int) {
        move(currentDirection, amount)
    }

    fun turn(direction: TurnDirection, amount: Int) {
        val directions = Direction.values()

        val currentDirectionIndex = directions.indexOf(currentDirection)

        val directionIndexOffset = amount / 90

        val newDirectionIndex = when (direction) {
            TurnDirection.LEFT -> (4 + currentDirectionIndex - directionIndexOffset) % 4
            TurnDirection.RIGHT -> (currentDirectionIndex + directionIndexOffset) % 4
        }

        currentDirection = directions[newDirectionIndex]
    }

}

enum class Direction {
    NORTH, EAST, SOUTH, WEST
}

enum class TurnDirection {
    LEFT, RIGHT
}