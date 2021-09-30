package connectFour

import kotlin.system.exitProcess

fun game(player1: String, player2: String, board: List<String>, rows: Int, columns: Int) {

    fun updateBoard(player: String, board: List<String>, move: Int): List<String> {
        val boardUpdate = board.toMutableList()
        loop@ for (row in rows downTo 1) {
            var line = board[row]
            val left = line.substring(0, move * 2 - 1)
            val right = line.substring(move * 2)
            var position = line[move * 2 - 1]
            if (position == ' ') {
                position = when (player) {
                    player1 -> 'o'
                    else -> '*'
                }
                line = left + position + right
                boardUpdate[row] = line
                break@loop
            } else {
                continue@loop
            }
        }

        return boardUpdate
    }


    fun turn(player: String, board: List<String>, columns: Int) {
        println("$player's turn:")
        val turn = readLine()!!
        val move: Int
        when {
            turn.matches(Regex("[1-$columns]")) -> {
                move = turn.toInt()
                if (board[1][move * 2 - 1] != ' ') {
                    println("Column $move is full")
                    turn(player, board, columns)
                } else {
                    val updatedBoard = updateBoard(player, board, move)
                    printBoard(updatedBoard)
                    val nextPlayer = when (player) {
                        player1 -> player2
                        else -> player1
                    }
                    turn(nextPlayer, updatedBoard, columns)
                }
            }
            turn == "end" -> {
                println("Game over!")
                exitProcess(0)
            }
            turn.matches(Regex("\\d\\d?\\d?")) -> {
                println("The column number is out of range (1 - $columns)")
                turn(player, board, columns)
            }
            else -> {
                println("Incorrect column number")
                turn(player, board, columns)
            }
        }
    }
    turn(player1, board, columns)
}

