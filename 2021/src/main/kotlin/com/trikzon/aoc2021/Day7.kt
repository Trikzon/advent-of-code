package com.trikzon.aoc2021

import kotlin.math.absoluteValue

fun main() {
    val input = getInputStringFromFile("/day7.txt")
    benchmark(Part.One, ::day7Part1, input, 328262, 5000)
    benchmark(Part.Two, ::day7Part2, input, 90040997, 500)
}

fun day7Part1(input: String): Int {
    val horizPositions = input.split(',').map { str -> str.toInt() }
    var smallestFuelAmount = Int.MAX_VALUE
    for (i in 0..horizPositions.maxOf { num -> num }) {
        var fuelAmount = 0
        for (horizPos in horizPositions) {
            fuelAmount += (horizPos - i).absoluteValue
        }
        if (smallestFuelAmount > fuelAmount) smallestFuelAmount = fuelAmount
    }
    return smallestFuelAmount
}

fun day7Part2(input: String): Int {
    val horizPositions = input.split(',').map { str -> str.toInt() }
    var smallestFuelAmount = Int.MAX_VALUE
    for (i in 0..horizPositions.maxOf { num -> num }) {
        var fuelAmount = 0
        for (horizPos in horizPositions) {
            (0..(horizPos - i).absoluteValue).forEach { num -> fuelAmount += num}
        }
        if (smallestFuelAmount > fuelAmount) smallestFuelAmount = fuelAmount
    }
    return smallestFuelAmount
}
