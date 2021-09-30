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
    println(board(rows, columns))
}