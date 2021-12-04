package com.trikzon.aoc2021

fun main() {
    val input = getInputStringFromFile("/day4.txt")

    println(dayFourPartOne(input))
    println(dayFourPartTwo(input))
}

fun dayFourPartOne(input: String): Int {
    val lines = input.lines().filter { line -> line.isNotEmpty() }

    val drawnNumbers = lines[0].split(',').map { str -> str.trim().toInt() }
    val boards = ArrayList<Board>()
    for (i in 1 until lines.size step 5) {
        val numbers = "${lines[i]} ${lines[i + 1]} ${lines[i + 2]} ${lines[i + 3]} ${lines[i + 4]}"
        boards.add(Board(numbers.split(' ').filter { str -> str.isNotEmpty() }.map { str -> str.trim().toInt() }))
    }
    for (drawnNumber in drawnNumbers) {
        for (board in boards) {
            board.mark(drawnNumber)
            if (board.checkForBingo()) {
                return board.sumOfUnmarked() * drawnNumber
            }
        }
    }
    return 0
}

fun dayFourPartTwo(input: String): Int {
    val lines = input.lines().filter { line -> line.isNotEmpty() }

    val drawnNumbers = lines[0].split(',').map { str -> str.trim().toInt() }
    var boards = ArrayList<Board>()
    for (i in 1 until lines.size step 5) {
        val numbers = "${lines[i]} ${lines[i + 1]} ${lines[i + 2]} ${lines[i + 3]} ${lines[i + 4]}"
        boards.add(Board(numbers.split(' ').filter { str -> str.isNotEmpty() }.map { str -> str.trim().toInt() }))
    }
    for (drawnNumber in drawnNumbers) {
        for (board in boards) {
            board.mark(drawnNumber)
            if (board.checkForBingo() && boards.size == 1) {
                return drawnNumber * board.sumOfUnmarked()
            }
        }
        boards = boards.filter { board -> !board.complete } as ArrayList<Board>
    }
    return 0
}

class Board(numbers: List<Int>) {
    private val numbers = Array(5 * 5) { 0 }
    private val marked = Array(5 * 5) { false }
    var complete = false

    init {
        for (i in numbers.indices) {
            this.numbers[i] = numbers[i]
        }
    }

    fun mark(num: Int) {
        for (i in numbers.indices) {
            if (numbers[i] == num) {
                marked[i] = true
                break
            }
        }
    }

    fun checkForBingo(): Boolean {
        // check rows
        for (i in 0 until 5) {
            if (marked[i * 5] && marked[i * 5 + 1] && marked[i * 5 + 2] && marked[i * 5 + 3] && marked[i * 5 + 4]) {
                complete = true
                return true
            }
        }
        // check columns
        for (i in 0 until 5) {
            if (marked[i] && marked[i + 5] && marked[i + 10] && marked[i + 15] && marked[i + 20]) {
                complete = true
                return true
            }
        }
        return false
    }

    fun sumOfUnmarked(): Int {
        var accumulator = 0
        for (i in numbers.indices) {
            if (!marked[i]) {
                accumulator += numbers[i]
            }
        }
        return accumulator
    }
}
