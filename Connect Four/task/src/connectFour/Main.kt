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
    val board = board(rows, columns)
    for (line in board) println(line)
    game(player1, player2, board, rows, columns)
}