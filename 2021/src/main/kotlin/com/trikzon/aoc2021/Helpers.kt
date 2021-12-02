package com.trikzon.aoc2021

fun getInputStringFromFile(path: String): String {
    return object {}.javaClass.getResource(path).readText()
}

enum class Part {
    One,
    Two,
}

fun <A, R> benchmark(part: Part, function: (A) -> R, arg: A, solution: R) {
    val times = Array<Long>(50000) { 0 }
    for (i in 0 until 50000) {
        val startTime = System.nanoTime()
        val result = function(arg)
        val endTime = System.nanoTime()

        val time = (endTime - startTime)
        assert(result == solution) {
            "Failed part ${part.name.lowercase()} in ${getTimeString(time)}. Result: $result. Solution: $solution"
        }
        times[i] = time
    }

    val averageTime = times.sum() / times.size

    println("Passed part ${part.name.lowercase()} in ${getTimeString(averageTime)}. Solution: $solution")
}

fun getTimeString(nanoSeconds: Long): String {
    val microSeconds = nanoSeconds / 1000f

    return if (microSeconds >= 1000f) {
        "${microSeconds / 1000f}ms"
    } else {
        "${microSeconds}µs"
    }
}

