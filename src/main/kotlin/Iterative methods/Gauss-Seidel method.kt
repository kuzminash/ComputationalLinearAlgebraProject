package `Iterative methods`

import Structures.Complex
import Structures.Matrix
import java.math.BigDecimal


fun easyGauss(L: Matrix, b: Matrix): Matrix {
    val x = Matrix(b.rows, b.cols)
    x.matrixArray[0][0] = b.matrixArray[0][0] / L.matrixArray[0][0]
    for (i in 1 until b.rows) {
        var acc = Complex(0.0, 0.0)
        for (j in 0 until i) {
            acc += L.matrixArray[i][j] * x.matrixArray[j][0]
        }
        x.matrixArray[i][0] = (b.matrixArray[i][0] - acc) / L.matrixArray[i][i]
    }
    return x;
}

fun solveGaussSeidel(matrix: Matrix, b: Matrix, epsiolon: Double): Solution {

    val L = Matrix(matrix.rows, matrix.cols)
    val U = Matrix(matrix.rows, matrix.cols)
    var isZero = false

    for (i in 0 until matrix.rows) {
        if (matrix.matrixArray[i][i].module() < Constants.Epsilon) isZero = true
        for (j in 0 until matrix.cols) {
            if (i < j) U.matrixArray[i][j] = matrix.matrixArray[i][j]
            else L.matrixArray[i][j] = matrix.matrixArray[i][j]
        }
    }

    if (isZero) return Solution(0)

    var iteration = 0

    var vector = Matrix(b.rows, b.cols)
    vector.matrixArray.forEach {
        it[0] =
            Complex(0.0, 0.0)
    }

    while (true) {

        if ((matrix * vector - b).norm() < epsiolon) return Solution(1, vector)

        val newVector = easyGauss(L, (U * ((-1.0).toBigDecimal())) * vector + b)
        iteration += 1
        if (iteration == 20) return Solution(0)

        if (newVector.norm() < vector.norm() + 1.0) {
            iteration = 0
        }
        vector = newVector
    }
}