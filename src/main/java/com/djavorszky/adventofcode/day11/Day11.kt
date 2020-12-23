package com.djavorszky.adventofcode.day11

import com.djavorszky.adventofcode.Day
import com.djavorszky.adventofcode.helper.InputSource

class Day11 : Day {
    override fun task1(inputSource: InputSource): Long {
        val floor = Floor(inputSource.input)

        return iterate(floor, shouldTask1OccupiedChange, shouldTask1FreeChange)
                .values
                .flatten()
                .filter { it == Floor.Tile.OCCUPIED }
                .count()
                .toLong()
    }

    override fun task2(inputSource: InputSource): Long {
        val floor = Floor(inputSource.input)

        return iterate(floor, shouldTask2OccupiedChange, shouldTask2FreeChange)
                .values
                .flatten()
                .filter { it == Floor.Tile.OCCUPIED }
                .count()
                .toLong()
    }

    private fun iterate(floor: Floor,
                        shouldOccupiedChange: (floor: Floor, x: Int, y: Int) -> Boolean,
                        shouldFreeChange: (floor: Floor, x: Int, y: Int) -> Boolean): Floor {

        var layoutChanged = true

        while (layoutChanged) {
            layoutChanged = false

            val newPlan = mutableListOf<Floor.Tile>()

            for (x in 0 until floor.numberOfLines) {
                for (y in 0 until floor.floorWidth) {
                    val newTile = when (floor.values[x][y]) {
                        Floor.Tile.OCCUPIED -> if (shouldOccupiedChange(floor, x, y)) {
                            layoutChanged = true

                            Floor.Tile.FREE
                        } else {
                            Floor.Tile.OCCUPIED
                        }
                        Floor.Tile.FREE -> if (shouldFreeChange(floor, x, y)) {
                            layoutChanged = true
                            Floor.Tile.OCCUPIED
                        } else {
                            Floor.Tile.FREE
                        }
                        Floor.Tile.FLOOR -> Floor.Tile.FLOOR
                    }

                    newPlan.add(newTile)
                }
            }

            floor.values = newPlan.chunked(floor.floorWidth)
        }

        return floor
    }

    private val shouldTask1FreeChange = { floor: Floor, x: Int, y: Int ->
        floor.getImmediateNeighbours(x, y).filter { it == Floor.Tile.OCCUPIED }
                .count() == 0
    }

    private val shouldTask1OccupiedChange = { floor: Floor, x: Int, y: Int ->
        floor.getImmediateNeighbours(x, y).filter { it == Floor.Tile.OCCUPIED }
                .count() >= 4
    }

    private val shouldTask2FreeChange = { floor: Floor, x: Int, y: Int ->
        floor.getSeenNeighbours(x, y).countOccupiedSeatsInAllDirections() == 0
    }

    private val shouldTask2OccupiedChange = { floor: Floor, x: Int, y: Int ->
        floor.getSeenNeighbours(x, y).countOccupiedSeatsInAllDirections() >= 5
    }

}