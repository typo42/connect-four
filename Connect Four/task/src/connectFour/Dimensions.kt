package connectFour

import java.util.*

fun dimensions(): Pair<Int, Int> {
    println("Set the board dimensions (Rows x Columns)")
    println("Press Enter for default (6 x 7)")
    val dimensions = readLine()!!
        .ifEmpty { "6 x 7" }
        .lowercase(Locale.getDefault())
        .filter { it.isDigit() || it.isLetter() }

    return when {
        dimensions.matches(Regex("[5-9]x[5-9]")) -> {
            val list = dimensions.split('x').map { it.toInt() }
            val rows = list[0]
            val columns = list[1]
            return Pair(rows, columns)
        }
        dimensions.matches(Regex("\\d?\\dx[5-9]")) -> {
            println("Board rows should be from 5 to 9")
            dimensions()
        }
        dimensions.matches(Regex("[5-9]x\\d?\\d")) -> {
            println("Board columns should be from 5 to 9")
            dimensions()
        }
        else -> {
            println("Invalid input")
            dimensions()
        }
    }
}


