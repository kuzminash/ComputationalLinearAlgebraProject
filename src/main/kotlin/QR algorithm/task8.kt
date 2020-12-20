package `QR algorithm`

import Solution3
import Structures.*
import `QR decomposition`.`Givens rotation`.solveTask4

fun solveTask8(a: Matrix, epsilon: Double) : Solution3 {

    var A = a
    var eigen = createId(A.rows)

    while (true) {

        val answer = solveTask4(A)
        val Q = answer.Q
        val R = answer.R

        eigen *= Q
        A = R * Q
        if (GershgorinCircles(A).checkRadii(epsilon)) {
            val newAnswer = mutableListOf<Complex>()
            for (i in 0 until A.rows) newAnswer.add(A.matrixArray[i][i])
            return Solution3(newAnswer, eigen)
        }
    }
}
