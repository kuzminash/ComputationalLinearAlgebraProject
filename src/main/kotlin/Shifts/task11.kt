package Shifts

import Solution3
import Structures.Complex
import Structures.Matrix
import Structures.createId
import Structures.findClosest
import `QR decomposition`.`Givens rotation`.GivensRotation
import `QR decomposition`.`Givens rotation`.solveTask3
import `QR decomposition`.`Givens rotation`.solveTask4

fun solveTask11(a : Matrix, epsilon:  Double) : Solution3 {
    var A = a.copy()
    val ort = createId(A.rows)
    val eigenVal = createId(A.rows)
    for (i in A.rows - 1 downTo 1) {

        while (true) {
            if (A.matrixArray[i][i - 1].module() + A.matrixArray[i - 1][i].module() <= epsilon / 2.0) {
                A.matrixArray[i][i - 1] = Complex(0.0, 0.0)
                A.matrixArray[i - 1][i] = Complex(0.0, 0.0)
                break
            }

            val number = findClosest(A, i)
            val history: MutableList<GivensRotation> = mutableListOf()

            val new = A - eigenVal * number
            val answer = solveTask4(new, history)

            for (h in history) solveTask3(ort, h.i, h.j, h.c, h.s)

            A = answer.R * answer.Q + eigenVal * number
            for (k in 0 until A.rows) for (j in 0 until A.cols)
                            if (kotlin.math.abs(k - j) >= 2) A.matrixArray[k][j] = Complex(0.0, 0.0)

        }
        eigenVal.matrixArray[i][i] = Complex(0.0, 0.0)
    }
    val newAnswer = mutableListOf<Complex>()
    for (j in 0 until A.rows) newAnswer.add(A.matrixArray[j][j])
    return Solution3(newAnswer, ort.transpose())
}