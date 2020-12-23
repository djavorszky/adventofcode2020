package com.djavorszky.adventofcode.day11

import java.util.*
import java.util.stream.Collectors

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


    fun getNeighbours(x: Int, y: Int): List<Tile> {
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