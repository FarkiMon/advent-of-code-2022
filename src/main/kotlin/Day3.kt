import java.lang.IllegalArgumentException

fun main() {
    val file = readResource("day3")

    // lowercase - 1 -> 26
    // uppercase - 27 -> 52

    // find sum of all common

    fun getPriority(char: Char): Int {
        return if (char.isLowerCase()) char.code - 96 else char.code - 38
    }

    fun part1(input: String): Int {
        val partitions: List<String> = input.split("(?<=\\G.{${input.count() / 2}})".toRegex())

        val char = partitions[0].first { c ->
            partitions[1].contains(c)
        }
        return getPriority(char)
    }

    fun part2(input: List<String>?): Int {
        // do 3 lines, match
        if (input == null) {
            throw IllegalArgumentException()
        }

        val chars = input.chunked(3).map {
            it[0].first { c ->
                it[1].contains(c) && it[2].contains(c)
            }
        }.sumOf { getPriority(it) }

        return chars
    }
    println(file?.sumOf { line -> part1(line) })
    println(part2(file))
}