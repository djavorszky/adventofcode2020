package com.djavorszky.adventofcode.day11

import com.djavorszky.adventofcode.Day
import com.djavorszky.adventofcode.helper.InputSource

class Day11 : Day {
    override fun task1(inputSource: InputSource): Long {
        val floor = Floor(inputSource.input)

        var layoutChanged = true

        while (layoutChanged) {
            layoutChanged = false

            val newPlan = mutableListOf<List<Floor.Tile>>()

            for (x in 0 until floor.numberOfLines) {
                val newRow = mutableListOf<Floor.Tile>()

                for (y in 0 until floor.floorWidth) {
                    val newTile = when (floor.values[x][y]) {
                        Floor.Tile.OCCUPIED -> if (floor.getNeighbours(x, y).filter { it == Floor.Tile.OCCUPIED }
                                        .count() >= 4) {
                            layoutChanged = true

                            Floor.Tile.FREE
                        } else {
                            Floor.Tile.OCCUPIED
                        }
                        Floor.Tile.FREE -> if (floor.getNeighbours(x, y).filter { it == Floor.Tile.OCCUPIED }
                                        .count() == 0) {
                            layoutChanged = true
                            Floor.Tile.OCCUPIED
                        } else {
                            Floor.Tile.FREE
                        }
                        Floor.Tile.FLOOR -> Floor.Tile.FLOOR
                    }

                    newRow.add(newTile);
                }

                newPlan.add(newRow);
            }

            floor.values = newPlan
        }

        return floor.values.flatten().filter { it == Floor.Tile.OCCUPIED }.count().toLong()
    }

    override fun task2(inputSource: InputSource): Long {
        TODO("Not yet implemented")
    }
}