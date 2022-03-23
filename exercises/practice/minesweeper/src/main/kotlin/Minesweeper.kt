data class MinesweeperBoard(val inputBoard: List<String>) {
    fun withNumbers(): List<String> =
        inputBoard.mapIndexed { row, str ->
            str.mapIndexed { col, cell -> when (cell) {
                '.' -> adjacentMines(row, col)
                else -> cell
            }
            }.joinToString("")
        }
    private fun adjacentMines(row: Int, col: Int): Char {
        val count =
            (Math.max(0, row - 1)..Math.min(inputBoard.size - 1, row + 1))
                .flatMap { i ->
                    (Math.max(0, col - 1)..Math.min(inputBoard[i].length - 1, col + 1))
                        .map { j ->
                            if (inputBoard[i][j] == '*') 1 else 0
                        }
                }
                .sum()
        return if (count > 0) (count + 48).toChar() else '.'
    }
}

fun main(args: Array<String>) {
    val a = MinesweeperBoard(
        listOf<String>(".", "*", ".", "*", ".", ".", ".", "*", ".", ".",
            ".", ".", "*", ".", ".", ".", ".", ".", ".", "."))
    println("Minesweeper board:")
    println(a.withNumbers())
}