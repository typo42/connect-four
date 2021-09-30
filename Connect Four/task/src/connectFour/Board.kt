package connectFour

fun board(rows: Int, columns: Int): List<String> {
    val board = mutableListOf<String>()
    var header = " "
    for (column in 1..columns) header += ("$column ")
    board.add(header)
    for (row in 0 until rows) {
        var line = "║"
        for (column in 1..columns) line += " ║"
        board += line
    }
    val last = mutableListOf("╚", "═╝")
    for (column in 1 until columns) last.add(1, "═╩")
    board += (last.joinToString(""))
    return board
}

fun printBoard(board: List<String>) {
    for (row in board) {
        println(row)
    }
}
