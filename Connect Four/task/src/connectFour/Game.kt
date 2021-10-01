package connectFour

import kotlin.system.exitProcess

fun game(board: Board, stats: Stats) {

    if (stats.gamesTotal > 1) println("Game #${stats.currentGame}")
    board.print()

    fun endGame() {
        println("Game over!")
        exitProcess(0)
    }

    fun nextPlayer(player: Player): Player {
        return when (player) {
            stats.player1 -> stats.player2
            else -> stats.player1
        }
    }

    fun printScores() {
        println("Score\n${stats.player1.name}: ${stats.player1.score} ${stats.player2.name}: ${stats.player2.score}")
    }

    fun connectFour(board: Board, stats: Stats): Boolean {
        var graph = ""
        for (row in 0 until board.rows) {
            for (column in 0 until board.columns) {
                graph += board.positions[row][column]
            }
            graph += "\n"
        }

        val x = when (stats.currentPlayer) {
            stats.player1 -> "[o]"
            else -> "[*]"
        }

        if (graph.contains(Regex("$x$x$x$x"))) return true

        val verticalRegex = mutableListOf<String>()
        var block = ""
        for (i in 0 until board.columns) {
            block += "."
        }
        for (i in 0 until board.columns) {
            val temp = block.substring(0, i) + x + block.substring(i + 1)
            verticalRegex += "$temp\\n$temp\\n$temp\\n$temp"
        }
        for (regex in verticalRegex) {
            if (graph.contains(Regex(regex))) return true
        }

        val diagonalRegex = mutableListOf<String>()
        loop@ for (i in 0 until board.columns) {
            val regex = mutableListOf<String>()
            for (k in 0..3) {
                val temp = Array(board.columns) { "." }
                if (i + k >= board.columns) {
                    continue@loop
                } else {
                    temp[i + k] = x
                }
                regex += temp.joinToString("")
            }
            diagonalRegex += regex.joinToString("\\n")
        }
        loop@ for (i in 0 until board.columns) {
            val regex = mutableListOf<String>()
            for (k in 0..3) {
                val temp = Array(board.columns) { "." }
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

    fun updateBoard(player: Player, board: Board, move: Int) {

        for (row in board.rows - 1 downTo 0) {
            if (board.positions[row][move] == " ") {
                board.positions[row][move] = when (player) {
                    stats.player1 -> "o"
                    else -> "*"
                }
                board.print()
                if (connectFour(board, stats)) {
                    println("Player ${player.name} won")
                    player.updateScore()
                    printScores()
                    if (stats.gamesTotal == 1 || stats.currentGame == stats.gamesTotal) endGame()
                    stats.nextRound()
                    game(Board(board.rows, board.columns), stats)
                }
                break
            } else {
                continue
            }
        }
    }

    fun turn(board: Board, stats: Stats) {
        if (!board.positions[0].contains(" ")) {
            println("It is a draw")
            endGame()
        }
        println("${stats.currentPlayer.name}'s turn:")
        val turn = readLine()!!
        val move: Int
        when {
            turn.matches(Regex("[1-${board.columns}]")) -> {
                move = turn.toInt() - 1
                if (board.positions[0][move] != " ") {
                    println("Column $move is full")
                    turn(board, stats)
                } else {
                    updateBoard(stats.currentPlayer, board, move)
                    stats.nextPLayer()
                    turn(board, stats)
                }
            }
            turn == "end" -> {
                endGame()

            }
            turn.matches(Regex("\\d\\d?\\d?")) -> {
                println("The column number is out of range (1 - ${board.columns})")
                turn(board, stats)
            }
            else -> {
                println("Incorrect column number")
                turn(board, stats)
            }
        }
    }

    turn(board, stats)

}
