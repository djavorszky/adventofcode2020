package com.djavorszky.adventofcode.day11

data class Visibility(
        val N: List<Floor.Tile>,
        val NE: List<Floor.Tile>,
        val E: List<Floor.Tile>,
        val SE: List<Floor.Tile>,
        val S: List<Floor.Tile>,
        val SW: List<Floor.Tile>,
        val W: List<Floor.Tile>,
        val NW: List<Floor.Tile>,
) {

    fun countOccupiedSeatsInAllDirections(): Int {
        var occupied = 0

        if (N.any { it == Floor.Tile.OCCUPIED }) {
            occupied += 1
        }

        if (NE.any { it == Floor.Tile.OCCUPIED }) {
            occupied += 1
        }

        if (E.any { it == Floor.Tile.OCCUPIED }) {
            occupied += 1
        }

        if (SE.any { it == Floor.Tile.OCCUPIED }) {
            occupied += 1
        }

        if (S.any { it == Floor.Tile.OCCUPIED }) {
            occupied += 1
        }

        if (SW.any { it == Floor.Tile.OCCUPIED }) {
            occupied += 1
        }

        if (W.any { it == Floor.Tile.OCCUPIED }) {
            occupied += 1
        }

        if (NW.any { it == Floor.Tile.OCCUPIED }) {
            occupied += 1
        }

        return occupied
    }


}