package connectFour

fun board(rows: Int, columns: Int): String {
    var board = " "
    for (column in 1..columns) board += ("$column ")
    for (row in 0 until rows) {
        var line = "║"
        for (column in 1..columns) line += " ║"
        board += ("\n" + line)
    }
    val last = mutableListOf("╚", "═╝")
    for (column in 1 until columns) last.add(1, "═╩")
    board += ("\n" + last.joinToString(""))
    return board
}
