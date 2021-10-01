package connectFour

fun main() {
    println("Connect Four")
    println("First player's name:")
    val player1 = readLine()!!
    println("Second player's name:")
    val player2 = readLine()!!
    val (rows, columns) = dimensions()
    println("$player1 VS $player2")
    println("$rows X $columns board")
//    val player1 = "Alex" // TEST
//    val player2 = "Ross" // TEST
//    val rows = 7 // TEST
//    val columns = 8 // TEST
    val board = List(rows) { MutableList(columns) { " " } }
    printBoard(board)
    game(player1, player2, board)
}