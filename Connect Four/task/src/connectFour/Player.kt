package connectFour

data class Player(val name: String, var score: Int = 0) {

    fun updateScore() {
        this.score++
    }
}