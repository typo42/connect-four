package connectFour

data class Stats(
    val player1: Player,
    val player2: Player,
    val gamesTotal: Int = 1,
    var currentGame: Int = 0,
    var currentPlayer: Player = player2
) {

    fun nextPLayer() {
        currentPlayer = when (currentPlayer) {
            player1 -> player2
            else -> player1
        }
    }

    fun nextRound() {
        currentGame++
        nextPLayer()
    }

}