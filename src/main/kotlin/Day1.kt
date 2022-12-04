import java.lang.IllegalArgumentException

fun main() {
    val calories = readResource("day1")

    fun fatElves(calories: List<String>?): List<Int> {

        if (calories != null) {
            val fatties = calories.joinToString(",")
                .split(",,")
                .map { fatty ->
                    fatty
                        .split(",")
                        .sumOf { fatElf -> fatElf.toInt() }
                }
            return fatties
        } else {
            throw IllegalArgumentException("No input")
        }
    }

    fun getFattest(): Int? {
        return fatElves(calories).maxOrNull()
    }

    fun getTopFatties(): Int {
        return fatElves(calories).sorted().takeLast(3).sum()
    }

    println(getFattest())
    println(getTopFatties())
}