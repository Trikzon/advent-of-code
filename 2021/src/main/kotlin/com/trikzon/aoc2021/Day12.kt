package com.trikzon.aoc2021

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val input = getInputStringFromFile("/day12.txt")
    benchmark(Part.One, ::day12Part1, input, 5252, 5000)
    benchmark(Part.One, ::day12Part2, input, 147784, 50)
}

fun day12Part1(input: String): Int {
    val caveConnections = HashMap<String, ArrayList<String>>()
    input.lines().forEach { line ->
        val caves = line.split('-')
        if (caves[1] == "start") {
            if (caveConnections["start"] == null) {
                caveConnections["start"] = ArrayList<String>().apply { add(caves[0]) }
            } else {
                caveConnections["start"]?.add(caves[0])
            }
        } else if (caves[0] == "end") {
            if (caveConnections[caves[1]] == null) {
                caveConnections[caves[1]] = ArrayList<String>().apply { add("end") }
            } else {
                caveConnections[caves[1]]?.add("end")
            }
        } else {
            if (caveConnections[caves[0]] == null) {
                if (caves[0] != "end") caveConnections[caves[0]] = ArrayList<String>().apply { add(caves[1]) }
            } else {
                if (caves[0] != "end") caveConnections[caves[0]]?.add(caves[1])
            }
            if (caveConnections[caves[1]] == null) {
                if (caves[0] != "start") caveConnections[caves[1]] = ArrayList<String>().apply { add(caves[0]) }
            } else {
                if (caves[0] != "start") caveConnections[caves[1]]?.add(caves[0])
            }
        }
    }
    return visitCave("start", LinkedList(), caveConnections)
}

fun visitCave(cave: String, path: LinkedList<String>, caveConnections: HashMap<String, ArrayList<String>>): Int {
    val newPath = LinkedList(path).apply { add(cave) }
    var paths = 0
    if (caveConnections[cave] != null) {
        for (connection in caveConnections[cave]!!) {
            if (connection == "end") {
                paths += 1
                continue
            }
            if (!hasBeenVisited(connection, newPath)) {
                paths += visitCave(connection, newPath, caveConnections)
            }
        }
    }
    return paths
}

fun hasBeenVisited(cave: String, path: LinkedList<String>): Boolean {
    if (cave[0].isUpperCase()) return false
    for (visited in path) {
        if (visited == cave) {
            return true
        }
    }
    return false
}

fun day12Part2(input: String): Int {
    val caveConnections = HashMap<String, ArrayList<String>>()
    input.lines().forEach { line ->
        val caves = line.split('-')
        if (caves[1] == "start") {
            if (caveConnections["start"] == null) {
                caveConnections["start"] = ArrayList<String>().apply { add(caves[0]) }
            } else {
                caveConnections["start"]?.add(caves[0])
            }
        } else if (caves[0] == "end") {
            if (caveConnections[caves[1]] == null) {
                caveConnections[caves[1]] = ArrayList<String>().apply { add("end") }
            } else {
                caveConnections[caves[1]]?.add("end")
            }
        } else {
            if (caveConnections[caves[0]] == null) {
                if (caves[0] != "end") caveConnections[caves[0]] = ArrayList<String>().apply { add(caves[1]) }
            } else {
                if (caves[0] != "end") caveConnections[caves[0]]?.add(caves[1])
            }
            if (caveConnections[caves[1]] == null) {
                if (caves[0] != "start") caveConnections[caves[1]] = ArrayList<String>().apply { add(caves[0]) }
            } else {
                if (caves[0] != "start") caveConnections[caves[1]]?.add(caves[0])
            }
        }
    }
    return visitCavePart2("start", LinkedList(), caveConnections)
}

fun visitCavePart2(cave: String, path: LinkedList<String>, caveConnections: HashMap<String, ArrayList<String>>): Int {
    val newPath = LinkedList(path).apply { add(cave) }
    var paths = 0
    if (caveConnections[cave] != null) {
        for (connection in caveConnections[cave]!!) {
            if (connection == "end") {
                paths += 1
                continue
            }
            if (!hasBeenVisitedPart2(connection, newPath)) {
                paths += visitCavePart2(connection, newPath, caveConnections)
            }
        }
    }
    return paths
}

fun hasBeenVisitedPart2(cave: String, path: LinkedList<String>): Boolean {
    if (cave[0].isUpperCase()) return false
    var alreadyDoubled = false
    for (visited in path) {
        if (visited[0].isLowerCase()) {
            if (path.count { i -> i == visited } >= 2) alreadyDoubled = true
        }
    }
    var timesVisited = 0
    for (visited in path) {
        if (visited == cave) {
            timesVisited++
            if (alreadyDoubled) {
                return true
            }
        }
        if (timesVisited == 2) return true
    }
    return false
}
