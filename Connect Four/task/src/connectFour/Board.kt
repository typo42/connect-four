package connectFour

fun printBoard(board: List<MutableList<String>>) {
    val rows = board.count()
    val columns = board[0].count()
    var header = " "
    for (column in 1..columns) header += ("$column ")
    println(header)
    for (row in 0 until rows) {
        val boardLine = "║" + board[row].joinToString("║") + "║"
        println(boardLine)
    }
    var bottom = "╚"
    for (column in 1 until columns) bottom += "═╩"
    bottom += "═╝"
    println(bottom)

}

