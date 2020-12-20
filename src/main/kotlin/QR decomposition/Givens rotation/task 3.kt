package `QR decomposition`.`Givens rotation`

import Structures.Complex
import Structures.Matrix
import java.math.BigDecimal

//Asymptotically works for O(n)
fun solveTask3(A : Matrix, i: Int, j: Int, c: Complex, s: Complex) {
    val ui = Matrix(A.rows, 1)
    val uj = Matrix(A.rows, 1)

    for (k in 0 until A.cols) {
        ui.matrixArray[k][0] = A.matrixArray[i][k]
        uj.matrixArray[k][0] = A.matrixArray[j][k]
    }

    val ui_new = ui * c + uj * s
    val uj_new = ui * (-s) + uj * c

    for (k in 0 until  A.cols) {
        A.matrixArray[i][k] = ui_new.matrixArray[k][0]
        A.matrixArray[j][k] = uj_new.matrixArray[k][0]
    }
}