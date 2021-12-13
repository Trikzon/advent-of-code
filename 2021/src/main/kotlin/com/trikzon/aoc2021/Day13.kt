package com.trikzon.aoc2021

import kotlin.math.absoluteValue

fun main() {
    val input = getInputStringFromFile("/day13.txt")
    benchmark(Part.One, ::day13Part1, input, 682, 50)
    benchmark(Part.Two, ::day13Part2, input, 682, 50)
}

fun day13Part1(input: String): Int {
    var points = ArrayList<Pair<Int, Int>>()
    val instructions = ArrayList<Pair<String, Int>>()
    input.lines().forEach { line ->
        if (line.split(' ')[0] == "fold") {
            val instruction = line.split(' ')[2].split('=')
            instructions.add(Pair(instruction[0], instruction[1].toInt()))
        } else {
            val point = line.split(',')
            points.add(Pair(point[0].toInt(), point[1].toInt()))
        }
    }

    val firstInstruction = instructions[0]
    points = if (firstInstruction.first == "x") {
        foldOnX(firstInstruction.second, points)
    } else {
        foldOnY(firstInstruction.second, points)
    }

    return points.size
}

fun foldOnX(x: Int, points: List<Pair<Int, Int>>): ArrayList<Pair<Int, Int>> {
    val foldedPoints = ArrayList<Pair<Int, Int>>()
    for (point in points) {
        if (point.first < x) {
            if (!foldedPoints.contains(point)) foldedPoints.add(point)
        } else {
            val foldedPoint = Pair((point.first - (x * 2)).absoluteValue, point.second)
            if (!foldedPoints.contains(foldedPoint)) foldedPoints.add(foldedPoint)
        }
    }
    return foldedPoints
}

fun foldOnY(y: Int, points: List<Pair<Int, Int>>): ArrayList<Pair<Int, Int>> {
    val foldedPoints = ArrayList<Pair<Int, Int>>()
    for (point in points) {
        if (point.second < y) {
            if (!foldedPoints.contains(point)) foldedPoints.add(point)
        } else {
            val foldedPoint = Pair(point.first, (point.second - (y * 2)).absoluteValue)
            if (!foldedPoints.contains(foldedPoint)) foldedPoints.add(foldedPoint)
        }
    }
    return foldedPoints
}

fun day13Part2(input: String): Int {
    var points = ArrayList<Pair<Int, Int>>()
    val instructions = ArrayList<Pair<String, Int>>()
    input.lines().forEach { line ->
        if (line.split(' ')[0] == "fold") {
            val instruction = line.split(' ')[2].split('=')
            instructions.add(Pair(instruction[0], instruction[1].toInt()))
        } else {
            val point = line.split(',')
            points.add(Pair(point[0].toInt(), point[1].toInt()))
        }
    }

    for (instruction in instructions) {
        points = if (instruction.first == "x") {
            foldOnX(instruction.second, points)
        } else {
            foldOnY(instruction.second, points)
        }
    }

    // Just print the letters. I don't feel like parsing them.
//    val display = Array(100) { Array(100) { '.' } }
//    for (point in points) {
//        display[point.first][point.second] = '#'
//    }
//    for (x in display.indices) {
//        for (y in display.indices.reversed()) {
//            print(display[x][y])
//        }
//        println()
//    }

    return points.size
}
