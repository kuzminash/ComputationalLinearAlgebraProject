package `QR decomposition`.`Householder transformation`

import Structures.Complex
import Structures.Matrix

//that's all 5th task:)
fun solveTask5(A: Matrix, v: Matrix): Matrix {
    return A - (v * Complex(2.0, 0.0)) * (v.transpose() * A)
}

fun solveTask5ButFromRight(A: Matrix, v: Matrix): Matrix {
    return A - (A * v * Complex(2.0, 0.0)) * (v.transpose())
}