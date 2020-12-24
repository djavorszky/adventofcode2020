package com.djavorszky.adventofcode.day12

import kotlin.math.abs

class Ship {

    private var currentDirection = Direction.EAST
    private var xLocation = 0
    private var yLocation = 0

    private val waypoint = Waypoint(10, 1)

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

    fun moveWaypoint(direction: Direction, amount: Int) {
        when (direction) {
            Direction.NORTH -> waypoint.y += amount
            Direction.EAST -> waypoint.x += amount
            Direction.SOUTH -> waypoint.y -= amount
            Direction.WEST -> waypoint.x -= amount
        }
    }

    fun moveToWaypoint(numberOfTimes: Int) {
        repeat(numberOfTimes) {
            val xDiff = waypoint.x - xLocation
            val yDiff = waypoint.y - yLocation

            xLocation = waypoint.x
            yLocation = waypoint.y

            waypoint.x += xDiff
            waypoint.y += yDiff
        }
    }

    fun rotateWaypoint(direction: TurnDirection, amount: Int) {
        val rotationCount = amount / 90

        repeat(rotationCount) {
            val xDiff = waypoint.x - xLocation
            val yDiff = waypoint.y - yLocation

            when (direction) {
                TurnDirection.LEFT -> waypoint.x = (xLocation - yDiff).also { waypoint.y = yLocation + xDiff }
                TurnDirection.RIGHT -> waypoint.x = (xLocation + yDiff).also { waypoint.y = yLocation - xDiff }
            }
        }
    }
}

enum class Direction {
    NORTH, EAST, SOUTH, WEST
}

enum class TurnDirection {
    LEFT, RIGHT
}