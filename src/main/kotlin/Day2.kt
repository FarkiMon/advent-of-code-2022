class Day2 {

    // A, X - rock(1) B, Y - paper(2), C, Z - scissors(3)
    // 0 - loss, 3 - draw, 6 - win

    enum class Weapon(
        val description: Pair<String, String>,
        val weight: Int
    ) {
        ROCK(Pair("A", "X"), 1),
        PAPER(Pair("B", "Y"), 2),
        SCISSORS(Pair("C", "Z"), 3);

        companion object {
            fun fromString(stringValue: String): Weapon {
                return values().first { weapon ->
                    weapon.description.first == stringValue || weapon.description.second == stringValue
                }
            }
        }

        fun beats(): Weapon {
            return when (this) {
                ROCK -> SCISSORS
                PAPER -> ROCK
                SCISSORS -> PAPER
            }
        }

        fun beatenBy(): Weapon {
            return when (this) {
                SCISSORS -> ROCK
                ROCK -> PAPER
                PAPER -> SCISSORS
            }
        }
    }

    enum class Outcome(
        val weight: Int,
        val description: String
    ) {
        LOSS(0, "X"),
        DRAW(3, "Y"),
        WIN(6, "Z");

        companion object {
            fun fromString(stringValue: String): Outcome {
                return values().first { element ->
                    element.description == stringValue
                }
            }
        }
    }

}

fun main() {

    fun play(opponent: Day2.Weapon, player: Day2.Weapon): Day2.Outcome {
        return if (opponent == player) Day2.Outcome.DRAW
        else if (opponent.beats() == player) Day2.Outcome.LOSS
        else Day2.Outcome.WIN
    }

    fun rig(opponent: Day2.Weapon, outcome: Day2.Outcome): Day2.Weapon {
        return when (outcome) {
            Day2.Outcome.DRAW -> opponent
            Day2.Outcome.LOSS -> opponent.beats()
            else -> opponent.beatenBy()
        }
    }

    fun games(strategy: List<String>?): Int {
        var score = 0
        strategy?.forEach { weapons ->
            val (opponentWeapon, playerWeapon) = weapons.split(" ")
            val opponent = Day2.Weapon.fromString(opponentWeapon)
            val player = Day2.Weapon.fromString(playerWeapon)
            val outcome = play(opponent, player)

//            println("outcome: $outcome weapon: $playerWeapon player weight: ${player.weight}")
//            println("score: ${player.weight + outcome.weight}")
            score += (player.weight + outcome.weight)
        }
        return score
    }

    fun riggedGame(strategy: List<String>?): Int {
        var score = 0

        strategy?.forEach { match ->
            val (opponentWeapon, outcomeString) = match.split(" ")
            val opponent = Day2.Weapon.fromString(opponentWeapon)
            val outcome = Day2.Outcome.fromString(outcomeString)
            val player = rig(opponent, outcome)

//            println("score: ${player.weight + outcome.weight}")
            score += (player.weight + outcome.weight)
        }
        return score
    }

    val strategy = readResource("day2")

    println(games(strategy))
    println(riggedGame(strategy))
}