package connectFour

data class Board(val rows: Int, val columns: Int) {

    var positions = List(rows) { MutableList(columns) { " " } }

    fun print() {
        var header = " "
        for (column in 1..columns) header += ("$column ")
        println(header)
        for (row in 0 until rows) {
            val boardLine = "║" + positions[row].joinToString("║") + "║"
            println(boardLine)
        }
        var bottom = "╚"
        for (column in 1 until columns) bottom += "═╩"
        bottom += "═╝"
        println(bottom)
    }
}




