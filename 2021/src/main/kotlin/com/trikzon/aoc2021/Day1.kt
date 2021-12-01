package com.trikzon.aoc2021

fun main(args: Array<String>) {
    val input = getInputStringFromFile("day1.txt")
    val depths = input.lines().map { line -> line.toInt() }

    benchmark(Part.One, ::partOne, depths, 1624)
    benchmark(Part.Two, ::partTwo, depths, 1653)
}

fun partOne(depths: List<Int>): Int {
    var numberOfIncreases = 0

    for (i in 1 until depths.size) {
        if (depths[i] > depths[i - 1]) {
            numberOfIncreases++
        }
    }
    return numberOfIncreases
}

fun partTwo(depths: List<Int>): Int {
    var numberOfIncreases = 0

    for (i in 1 until depths.size - 2) {
        val iPlusNext = depths[i] + depths[i + 1]

        val firstSum = depths[i - 1] + iPlusNext
        val secondSum = iPlusNext + depths[i + 2]

        if (firstSum < secondSum) {
            numberOfIncreases++
        }
    }
    return numberOfIncreases
}
