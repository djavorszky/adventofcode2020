package com.djavorszky.adventofcode.day11

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FloorTest {

    @Test
    fun `floor construction works`() {
        val input = listOf("#.L", "###")

        val actual = Floor(input)

        val expected = listOf(
                listOf(Floor.Tile.OCCUPIED, Floor.Tile.FLOOR, Floor.Tile.FREE),
                listOf(Floor.Tile.OCCUPIED, Floor.Tile.OCCUPIED, Floor.Tile.OCCUPIED)
        )

        assertThat(actual.values).isEqualTo(expected)
        assertThat(actual.floorWidth).isEqualTo(3)
        assertThat(actual.numberOfLines).isEqualTo(2)
    }


    @Test
    fun `first neighbours are returned`() {
        val input = listOf(
                "#.L",
                ".L#",
                "L#.",
                "#.L")

        val actual = Floor(input)

        val expectedNeighbours = listOf(Floor.Tile.FLOOR, Floor.Tile.FLOOR, Floor.Tile.FREE)


        assertThat(actual.getImmediateNeighbours(0, 0)).isEqualTo(expectedNeighbours)
    }

    @Test
    fun `last neighbours are returned`() {
        val input = listOf(
                "#.L",
                ".L#",
                "L#.",
                "#.L")

        val actual = Floor(input)

        val expectedNeighbours = listOf(Floor.Tile.OCCUPIED, Floor.Tile.FLOOR, Floor.Tile.FLOOR)

        assertThat(actual.getImmediateNeighbours(3, 2)).isEqualTo(expectedNeighbours)
    }

    @Test
    fun `last row neighbours are returned`() {
        val input = listOf(
                "#.L",
                ".L#",
                "L#.",
                "#.L")

        val actual = Floor(input)

        val expectedNeighbours =
                listOf(Floor.Tile.FREE, Floor.Tile.OCCUPIED, Floor.Tile.OCCUPIED, Floor.Tile.FLOOR, Floor.Tile.FREE)

        assertThat(actual.getImmediateNeighbours(3, 1)).isEqualTo(expectedNeighbours)
    }

    @Test
    fun `first row neighbours are returned`() {
        val input = listOf(
                "#.L",
                ".L#",
                "L#.",
                "#.L")

        val actual = Floor(input)

        val expectedNeighbours =
                listOf(Floor.Tile.OCCUPIED, Floor.Tile.FLOOR,
                        Floor.Tile.FREE, Floor.Tile.FREE, Floor.Tile.OCCUPIED)

        assertThat(actual.getImmediateNeighbours(0, 1)).isEqualTo(expectedNeighbours)
    }

    @Test
    fun `middle neighbours are returned`() {
        val input = listOf(
                "#.L",
                ".L#",
                "L#.",
                "#.L")

        val actual = Floor(input)

        val expectedNeighbours =
                listOf(Floor.Tile.OCCUPIED, Floor.Tile.FLOOR, Floor.Tile.FREE,
                        Floor.Tile.FLOOR, Floor.Tile.OCCUPIED,
                        Floor.Tile.FREE, Floor.Tile.OCCUPIED, Floor.Tile.FLOOR)

        assertThat(actual.getImmediateNeighbours(1, 1)).isEqualTo(expectedNeighbours)
    }
}