package `Iterative methods`

import Structures.*
import Constants
import kotlin.random.Random


fun solveFixedPoint(matrix: Matrix, b: Matrix) : Solution {

    var vector = Matrix(b.rows, b.cols)

    vector.matrixArray.forEach {it[0] =
                    Complex(0.0, 0.0)}

    val allIn = GershgorinCircles(matrix).liesIn()
    var notFound = true
    var iteration = 0
    while (notFound) {

        val newVector = matrix * vector + b
        if ((vector - newVector).norm() < Constants.Epsilon.toBigDecimal()) {
            notFound = false
            break
        }

        iteration += 1
        if (iteration == 20 && !allIn) break

        if (newVector.norm() < vector.norm() + 1.0.toBigDecimal()) iteration = 0
        vector = newVector
    }
    return if (notFound) Solution(0)
    else Solution(1, vector)
}