package com.djavorszky.adventofcode.day11

import java.util.*
import java.util.stream.Collectors
import kotlin.math.min

class Floor(input: List<String>) {

    var values: List<List<Tile>>
    val floorWidth: Int
    val numberOfLines: Int

    init {
        values = input.stream()
                .map {
                    it.map { c -> Tile.from(c) }
                }
                .collect(Collectors.toList())

        floorWidth = input.first().length
        numberOfLines = input.size
    }


    fun getImmediateNeighbours(x: Int, y: Int): List<Tile> {
        val currentRow = values[x]

        val previousRow = if (x != 0) {
            Optional.of(values[x - 1])
        } else {
            Optional.empty()
        }

        val nextRow = if (x != numberOfLines - 1) {
            Optional.of(values[x + 1])
        } else {
            Optional.empty()
        }

        val result = mutableListOf<Tile>()

        if (y != 0) {
            previousRow.ifPresent { result.add(it[y - 1]) }
            result.add(currentRow[y - 1])
            nextRow.ifPresent { result.add(it[y - 1]) }
        }

        previousRow.ifPresent { result.add(it[y]) }
        nextRow.ifPresent { result.add(it[y]) }

        if (y != floorWidth - 1) {
            previousRow.ifPresent { result.add(it[y + 1]) }
            result.add(currentRow[y + 1])
            nextRow.ifPresent { result.add(it[y + 1]) }
        }

        return result
    }

    fun getSeenNeighbours(x: Int, y: Int): Visibility {
        return Visibility(
                N = getNorth(x, y),
                NE = getNorthEast(x, y),
                E = getEast(x, y),
                SE = getSouthEast(x, y),
                S = getSouth(x, y),
                SW = getSouthWest(x, y),
                W = getWest(x, y),
                NW = getNorthWest(x, y),
        )
    }

    private fun getNorthWest(x: Int, y: Int): List<Tile> {
        if (x == 0 || y == 0) {
            return emptyList()
        }

        val end = min(x, y)

        val neighbours = mutableListOf<Tile>()

        for (i in 1..end) {
            val tile = values[x - i][y - i]

            neighbours.add(tile)

            if (tile != Tile.FLOOR) {
                break
            }
        }

        return neighbours
    }


    private fun getNorthEast(x: Int, y: Int): List<Tile> {
        if (x == 0 || y == floorWidth) {
            return emptyList()
        }

        val end = min(x, floorWidth - y - 1)

        val neighbours = mutableListOf<Tile>()

        for (i in 1..end) {
            val tile = values[x - i][y + i]

            neighbours.add(tile)

            if (tile != Tile.FLOOR) {
                break
            }
        }

        return neighbours
    }

    private fun getSouthEast(x: Int, y: Int): List<Tile> {
        if (x == numberOfLines || y == floorWidth) {
            return emptyList()
        }

        val end = min(numberOfLines - x - 1, floorWidth - y - 1)

        val neighbours = mutableListOf<Tile>()

        for (i in 1..end) {
            val tile = values[x + i][y + i]

            neighbours.add(tile)

            if (tile != Tile.FLOOR) {
                break
            }
        }

        return neighbours
    }

    private fun getSouthWest(x: Int, y: Int): List<Tile> {
        if (x == numberOfLines || y == 0) {
            return emptyList()
        }

        val end = min(numberOfLines - x - 1, y)

        val neighbours = mutableListOf<Tile>()

        for (i in 1..end) {
            val tile = values[x + i][y - i]

            neighbours.add(tile)

            if (tile != Tile.FLOOR) {
                break
            }
        }

        return neighbours
    }


    private fun getWest(x: Int, y: Int): List<Tile> {
        val neighbours = mutableListOf<Tile>()

        for (i in y - 1 downTo 0) {
            val tile = values[x][i]

            neighbours.add(tile)

            if (tile != Tile.FLOOR) {
                break
            }
        }

        return neighbours
    }

    private fun getEast(x: Int, y: Int): List<Tile> {
        val neighbours = mutableListOf<Tile>()

        for (i in y + 1 until floorWidth) {
            val tile = values[x][i]

            neighbours.add(tile)

            if (tile != Tile.FLOOR) {
                break
            }
        }

        return neighbours
    }

    private fun getNorth(x: Int, y: Int): List<Tile> {
        if (x == 0) {
            return emptyList()
        }

        val neighbours = mutableListOf<Tile>()

        for (i in x - 1 downTo 0) {
            val tile = values[i][y]

            neighbours.add(tile)

            if (tile != Tile.FLOOR) {
                break
            }
        }

        return neighbours
    }

    private fun getSouth(x: Int, y: Int): List<Tile> {
        if (x == numberOfLines) {
            return emptyList()
        }

        val neighbours = mutableListOf<Tile>()

        for (i in x + 1 until numberOfLines) {
            val tile = values[i][y]

            neighbours.add(tile)

            if (tile != Tile.FLOOR) {
                break
            }
        }

        return neighbours
    }

    enum class Tile {
        OCCUPIED,
        FREE,
        FLOOR;

        companion object {
            fun from(e: Char): Tile {
                return when (e) {
                    '#' -> OCCUPIED
                    'L' -> FREE
                    else -> FLOOR
                }
            }
        }
    }

}