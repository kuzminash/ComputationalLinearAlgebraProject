package `Iterative methods`

import Structures.Complex
import Structures.Matrix


fun easyGauss(L: Matrix, b: Matrix): Matrix {
    val x = Matrix(b.rows, b.cols)
    x.matrixArray[0][0] = Complex(b.matrixArray[0][0].real / L.matrixArray[0][0].real, 0.0.toBigDecimal())
    for (i in 1 until b.rows) {
        var acc = 0.0.toBigDecimal()
        for (j in 0 until i) {
            acc += (L.matrixArray[i][j].real * x.matrixArray[j][0].real)
        }
        x.matrixArray[i][0] = Complex((b.matrixArray[i][0].real - acc) / L.matrixArray[i][i].real, 0.0.toBigDecimal())
    }
    return x;
}

fun solveGaussSeidel(matrix: Matrix, b: Matrix): Solution {

    val L = Matrix(matrix.rows, matrix.cols)
    val U = Matrix(matrix.rows, matrix.cols)
    var isZero = false

    for (i in 0 until matrix.rows) {
        if (matrix.matrixArray[i][i].isEqual(Complex(0.0, 0.0))) isZero = true
        for (j in 0 until matrix.cols) {
            if (i < j) U.matrixArray[i][j] = matrix.matrixArray[i][j]
            else L.matrixArray[i][j] = matrix.matrixArray[i][j]
        }
    }

    if (isZero) return Solution(0)

    var notFound = true
    var iteration = 0

    var vector = Matrix(b.rows, b.cols)
    vector.matrixArray.forEach {
        it[0] =
            Complex(0.0, 0.0)
    }

    while (notFound) {

        if ((matrix * vector - b).norm() < Constants.Epsilon.toBigDecimal()) return Solution(1, vector)

        val newVector = easyGauss(L, (U * ((-1.0).toBigDecimal())) * vector + b)
        iteration += 1
        if (iteration == 20) return Solution(0)

        if (newVector.norm() < vector.norm() + 1.0.toBigDecimal()) {
            iteration = 0
        }
        vector = newVector
    }
    return Solution(0)
}