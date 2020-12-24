package com.djavorszky.adventofcode.day13

data class Bus(val id: Int) {
    fun firstDepartureAfter(time: Int): Int {
        return (time / id) * id + id
    }
}