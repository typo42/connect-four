package connectFour

fun main() {
    println("Connect Four")

    println("First player's name:")
    val player1 = Player(readLine()!!)
    println("Second player's name:")
    val player2 = Player(readLine()!!)

    val (rows, columns) = dimensions()
    val board = Board(rows, columns)

    val gamesTotal = singleOrMultiple()

//    val player1 = Player("Alex") // TEST
//    val player2 = Player("Ross") // TEST
//    val rows = 7 // TEST
//    val columns = 8 // TEST
//    val board = Board(rows, columns) // TEST

    val stats = Stats(player1, player2, gamesTotal)


    println("${player1.name} VS ${player2.name}")
    println("$rows X $columns board")
    println( when (gamesTotal) {
        1 -> "Single game"
        else -> "Total $gamesTotal games"
    })

    game(board, stats)
}