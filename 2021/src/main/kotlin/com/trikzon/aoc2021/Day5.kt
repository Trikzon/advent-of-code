package com.trikzon.aoc2021

import kotlin.math.max
import kotlin.math.min

fun main() {
    val input = getInputStringFromFile("/day5.txt")
    benchmark(Part.One, ::dayFivePartOne, input, 5442)
    benchmark(Part.Two, ::dayFivePartTwo, input, 19571)
}

fun dayFivePartOne(input: String): Int {
    val points = HashMap<Pair<Int, Int>, Int>()
    input.lines().forEach { line ->
        val coords = line.split(" -> ")
        val x1 = coords[0].split(',')[0].toInt()
        val y1 = coords[0].split(',')[1].toInt()
        val x2 = coords[1].split(',')[0].toInt()
        val y2 = coords[1].split(',')[1].toInt()
        if (x1 == x2) { // rows
            for (y in min(y1, y2)..max(y1, y2)) {
                points[Pair(x1, y)] = points.getOrPut(Pair(x1, y)) { 0 } + 1
            }
        } else if (y1 == y2) { // columns
            for (x in min(x1, x2)..max(x1, x2)) {
                points[Pair(x, y1)] = points.getOrPut(Pair(x, y1)) { 0 } + 1
            }
        }
    }
    return points.values.count { value -> value >= 2 }
}

fun dayFivePartTwo(input: String): Int {
    val points = HashMap<Pair<Int, Int>, Int>()
    input.lines().forEach { line ->
        val coords = line.split(" -> ")
        val x1 = coords[0].split(',')[0].toInt()
        val y1 = coords[0].split(',')[1].toInt()
        val x2 = coords[1].split(',')[0].toInt()
        val y2 = coords[1].split(',')[1].toInt()
        if (x1 == x2) { // rows
            for (y in min(y1, y2)..max(y1, y2)) {
                points[Pair(x1, y)] = points.getOrPut(Pair(x1, y)) { 0 } + 1
            }
        } else if (y1 == y2) { // columns
            for (x in min(x1, x2)..max(x1, x2)) {
                points[Pair(x, y1)] = points.getOrPut(Pair(x, y1)) { 0 } + 1
            }
        } else { // diagonals
            val deltaX = if (x1 > x2) -1 else 1
            val deltaY = if (y1 > y2) -1 else 1
            for (i in 0..max(x1, x2) - min(x1, x2)) {
                points[Pair(x1 + (deltaX * i), y1 + (deltaY * i))] = points.getOrPut(Pair(x1 + (deltaX * i), y1 + (deltaY * i))) { 0 } + 1
            }
        }
    }
    return points.values.count { value -> value >= 2 }
}
