package com.trikzon.aoc2021


// Solved using Dijkstra's Shortest Path Algorithm https://www.youtube.com/watch?v=pVfj6mxhdMw
fun main() {
    val input = getInputStringFromFile("/day15.txt")
    benchmark(Part.One, ::day15Part1, input, 398, 5)
    benchmark(Part.Two, ::day15Part2, input, 2817, 1)
}

fun day15Part1(input: String): Int {
    val xLen = input.lines()[0].length
    val yLen = input.lines().size

    val grid = Array(xLen) { Array(yLen) { 0 } }
    for (x in 0 until xLen) {
        for (y in 0 until yLen) {
            grid[x][y] = input.lines()[y][x].digitToInt()
        }
    }

    val visited = Array(xLen) { Array(yLen) { false } }
    val distancesFromStart = Array(xLen) { Array(yLen) { Int.MAX_VALUE} }
    val prevVertex = Array(xLen) { Array<Pair<Int, Int>?>(yLen) { null } }
    distancesFromStart[0][0] = 0

    var vertex = Pair(0, 0)

    // While a vertex hasn't been visited:
    while (visited.map { v -> v.count { r -> !r } }.count { i -> i > 0 } > 0) {
        for (neighbor in getNeighbors(vertex.first, vertex.second, xLen, yLen)) {
            if (visited[neighbor.first][neighbor.second]) continue

            val newDistance = distancesFromStart[vertex.first][vertex.second] + grid[neighbor.first][neighbor.second]
            if (distancesFromStart[neighbor.first][neighbor.second] > newDistance) {
                prevVertex[neighbor.first][neighbor.second] = vertex
                distancesFromStart[neighbor.first][neighbor.second] = newDistance
            }
        }
        visited[vertex.first][vertex.second] = true

        var newVertex = Pair(0, 0)
        var newVertexDistance = Int.MAX_VALUE
        for (x in 0 until xLen) {
            for (y in 0 until yLen) {
                if (!visited[x][y]) {
                    val distanceFromStart = distancesFromStart[x][y]
                    if (distanceFromStart < newVertexDistance) {
                        newVertex = Pair(x, y)
                        newVertexDistance = distanceFromStart
                    }
                }
            }
        }
        vertex = newVertex
    }

    return distancesFromStart[xLen - 1][yLen - 1]
}

fun getNeighbors(x: Int, y: Int, xLen: Int, yLen: Int): List<Pair<Int, Int>> {
    val list = ArrayList<Pair<Int, Int>>()
    if (x > 0) list.add(Pair(x - 1, y))
    if (y > 0) list.add(Pair(x, y - 1))
    if (x < xLen - 1) list.add(Pair(x + 1, y))
    if (y < yLen - 1) list.add(Pair(x, y + 1))
    return list
}

fun day15Part2(input: String): Int {
    var xLen = input.lines()[0].length
    var yLen = input.lines().size

    val smallGrid = Array(xLen) { Array(yLen) { 0 } }
    for (x in 0 until xLen) {
        for (y in 0 until yLen) {
            smallGrid[x][y] = input.lines()[y][x].digitToInt()
        }
    }

    val grid = Array(xLen * 5) { Array(yLen * 5) { 0 } }
    for (chunkX in 0 until 5) {
        for (chunkY in 0 until 5) {
            for (x in 0 until xLen) {
                for (y in 0 until yLen) {
                    var newRisk = smallGrid[x][y] + (chunkX + chunkY)
                    if (newRisk > 9) newRisk -= 9
                    grid[x + (xLen * chunkX)][y + (yLen * chunkY)] = newRisk
                }
            }
        }
    }
    xLen *= 5
    yLen *= 5

    val visited = Array(xLen) { Array(yLen) { false } }
    val distancesFromStart = Array(xLen) { Array(yLen) { Int.MAX_VALUE} }
    val prevVertex = Array(xLen) { Array<Pair<Int, Int>?>(yLen) { null } }
    distancesFromStart[0][0] = 0

    var vertex = Pair(0, 0)

    // While a vertex hasn't been visited:
    while (visited.map { v -> v.count { r -> !r } }.count { i -> i > 0 } > 0) {
        for (neighbor in getNeighbors(vertex.first, vertex.second, xLen, yLen)) {
            if (visited[neighbor.first][neighbor.second]) continue

            val newDistance = distancesFromStart[vertex.first][vertex.second] + grid[neighbor.first][neighbor.second]
            if (distancesFromStart[neighbor.first][neighbor.second] > newDistance) {
                prevVertex[neighbor.first][neighbor.second] = vertex
                distancesFromStart[neighbor.first][neighbor.second] = newDistance
            }
        }
        visited[vertex.first][vertex.second] = true

        // TODO: Look into using a priority queue instead of this...
        var newVertex = Pair(0, 0)
        var newVertexDistance = Int.MAX_VALUE
        for (x in 0 until xLen) {
            for (y in 0 until yLen) {
                if (!visited[x][y]) {
                    val distanceFromStart = distancesFromStart[x][y]
                    if (distanceFromStart < newVertexDistance) {
                        newVertex = Pair(x, y)
                        newVertexDistance = distanceFromStart
                    }
                }
            }
        }
        vertex = newVertex
    }

    return distancesFromStart[xLen - 1][yLen - 1]
}
