package `Applying QR algorithm`

import Solution3
import Structures.Complex
import Structures.GershgorinCircles
import Structures.Matrix
import Structures.createId
import `QR decomposition`.`Givens rotation`.GivensRotation
import `QR decomposition`.`Givens rotation`.solveTask3
import `QR decomposition`.`Givens rotation`.solveTask4
import `Tridiagonal matrix`.Solution4
import java.lang.Integer.min
import java.lang.Math.abs

fun wowwowTimes(R: Matrix, Q: Matrix): Matrix {
    val newMatrix = Matrix(R.rows, R.cols)
    for (i in 0 until R.rows) {
        for (j in 0 until R.cols) {
            for (k in i until min(R.rows, i + 3)) {
                newMatrix.matrixArray[i][j] += R.matrixArray[i][k] * Q.matrixArray[k][j]
            }
        }
    }
    return newMatrix
}

fun solveTask10(a: Matrix, epsilon: Double): Solution3 {

    val A = a.copy()
    var current: Matrix = A
    val eigen = createId(A.rows)

    while (true) {
        val history: MutableList<GivensRotation> = mutableListOf()
        val answer = solveTask4(current, history)
        for (h in history) {
            solveTask3(eigen, h.i, h.j, h.c, h.s)
        }
        current = wowwowTimes(answer.R, answer.Q)
        for (i in 0 until current.rows) {
            for (j in 0 until current.cols) {
                if (abs(i - j) >= 2) current.matrixArray[i][j] = Complex(0.0, 0.0)
            }
        }
        if (GershgorinCircles(current).checkRadii(epsilon)) {
            val newAnswer = mutableListOf<Complex>()
            for (i in 0 until current.rows) newAnswer.add(current.matrixArray[i][i])
            return Solution3(newAnswer, eigen.transpose())
        }
    }
}
