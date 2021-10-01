package connectFour

import kotlin.system.exitProcess

fun game(player1: String, player2: String, board: List<MutableList<String>>) {

    fun endGame() {
        println("Game over!")
        exitProcess(0)
    }

    fun connectFour(board: List<MutableList<String>>, player: String): Boolean {
        val rows = board.count()
        val columns = board[0].count()
        var graph = ""
        for (row in 0 until rows) {
            for (column in 0 until columns) {
                graph += board[row][column]
            }
            graph += "\n"
        }

        val x = when (player) {
            player1 -> "[o]"
            else -> "[*]"
        }

        if (graph.contains(Regex("$x$x$x$x"))) return true

        val verticalRegex = mutableListOf<String>()
        var block = ""
        for (i in 0 until columns) {
            block += "."
        }
        for (i in 0 until columns) {
            val temp = block.substring(0, i) + x + block.substring(i + 1)
            verticalRegex += "$temp\\n$temp\\n$temp\\n$temp"
        }
        for (regex in verticalRegex) {
            if (graph.contains(Regex(regex))) return true
        }

        val diagonalRegex = mutableListOf<String>()
        loop@for (i in 0 until columns) {
            val regex = mutableListOf<String>()
            for (k in 0..3) {
                val temp = Array(columns) { "." }
                if (i + k >= columns) {
                    continue@loop
                } else {
                    temp[i + k] = x
                }
                regex += temp.joinToString("")
            }
            diagonalRegex += regex.joinToString("\\n")
        }
        loop@for (i in 0 until columns) {
            val regex = mutableListOf<String>()
            for (k in 0..3) {
                val temp = Array(columns) { "." }
                if (i - k < 0) {
                    continue@loop
                } else {
                    temp[i - k] = x
                }
                regex += temp.joinToString("")
            }
            diagonalRegex += regex.joinToString("\\n")
        }
        for (regex in diagonalRegex) {
            if (graph.contains(Regex(regex))) return true
        }

        return false

    }

    fun updateBoard(player: String, board: List<MutableList<String>>, move: Int): List<MutableList<String>> {

        val rows = board.count()
        for (row in rows - 1 downTo 0) {
            if (board[row][move] == " ") {
                board[row][move] = when (player) {
                    player1 -> "o"
                    else -> "*"
                }
                printBoard(board)
                if (connectFour(board, player)) {
                    println("Player $player won")
                    endGame()
                }
                break
            } else {
                continue
            }
        }
        return board
    }

    fun turn(player: String, board: List<MutableList<String>>) {
        val columns = board[0].count()
        if (!board[0].contains(" ")) {
            println("It is a draw")
            endGame()
        }
        println("$player's turn:")
        val turn = readLine()!!
        val move: Int
        when {
            turn.matches(Regex("[1-$columns]")) -> {
                move = turn.toInt() - 1
                if (board[0][move] != " ") {
                    println("Column $move is full")
                    turn(player, board)
                } else {
                    val updatedBoard = updateBoard(player, board, move)
//                    printBoard(updatedBoard)
                    val nextPlayer = when (player) {
                        player1 -> player2
                        else -> player1
                    }
                    turn(nextPlayer, updatedBoard)
                }
            }
            turn == "end" -> {
                endGame()

            }
            turn.matches(Regex("\\d\\d?\\d?")) -> {
                println("The column number is out of range (1 - $columns)")
                turn(player, board)
            }
            else -> {
                println("Incorrect column number")
                turn(player, board)
            }
        }
    }
    turn(player1, board)
}