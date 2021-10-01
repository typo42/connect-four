package connectFour

import java.util.*

fun singleOrMultiple(): Int {
//    println("Do you want to play single or multiple games?")
//    println("For a single game, input 1 or press Enter")
//    println("Input a number of games:")
//    val userInput = readLine()!!
//    if (userInput.isEmpty()) return 1
//    if (!userInput.matches(Regex("[\\d]"))) {
//        println("Invalid input")
//        singleOrMultiple()
//    }
//    return userInput.toInt()

    val inputFormat = Regex("\\d+")

    while (true) {
        println(
            "Do you want to play single or multiple games?\n" +
                    "For a single game, input 1 or press Enter\n" +
                    "Input a number of games:"
        )
        val resp = readLine()!!
        if (resp.isEmpty()) {
            return 1
        } else if (inputFormat.matches(resp)) {
            if (resp.toInt() > 0) return resp.toInt() else println("Invalid input")
        } else {
            println("Invalid input")
        }
    }
}
