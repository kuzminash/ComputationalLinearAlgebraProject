package `QR algorithm`

import Structures.Complex
import Structures.Matrix
import java.math.BigDecimal
import java.math.RoundingMode

fun solveTask7(A: Matrix, epsilon: Double): Solution2 {

    val vec1 = Matrix(A.rows, 1)
    val time = System.currentTimeMillis()

    for (i in 0 until A.rows) vec1.matrixArray[i][0] = Complex(BigDecimal(Constants.rand.nextDouble()).setScale(Constants.Scale, RoundingMode.UP),
                                                                BigDecimal(Constants.rand.nextDouble()).setScale(Constants.Scale, RoundingMode.UP))
    var vec1Norm = vec1 * (1.0 / vec1.norm())

    while (true) {

        if (System.currentTimeMillis() - time >= 60 * 60 * 1000) {
            return Solution2(Complex(0.0, 0.0), Matrix(A.rows, 1))
        }

        val newAA = (vec1Norm.conjugate() * A * vec1Norm)

        if ((A * vec1Norm - vec1Norm * newAA).norm() < epsilon) {
            return Solution2(newAA.matrixArray[0][0], vec1Norm)
        }

        val next = A * vec1Norm
        vec1Norm = next * (1.0 / next.norm())
    }
}