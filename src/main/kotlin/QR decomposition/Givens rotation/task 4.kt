package `QR decomposition`.`Givens rotation`

import Structures.Complex
import Structures.Matrix
import Structures.createId
import Structures.createOne
import java.math.BigDecimal
import java.math.RoundingMode

data class GivensRotation(val i: Int, val j: Int, val c: Complex, val s: Complex)

fun solveTask4(a: Matrix, history: MutableList<GivensRotation> = mutableListOf()): Solution {
    val A = a.copy()
    val orthogonal = createId(A.rows)
    for (i in 0 until A.cols) {
        var where = -1
        for (j in i until A.rows) {
            if (A.matrixArray[j][i].module() > Constants.smallerEpsilon) {
                where = j
                break
            }
        }
        if (where == -1) continue
        if (i != where) {
            solveTask3(A, i, where, Complex(0.0, 0.0), Complex(1.0, 0.0))
            solveTask3(orthogonal, i, where, Complex(0.0, 0.0), Complex(1.0, 0.0))
            history.add(GivensRotation(i, where, Complex(0.0, 0.0), Complex(1.0, 0.0)))
        }
        for (j in i + 1 until A.rows) {
            if (A.matrixArray[j][i].module() > Constants.smallerEpsilon) {
                //trying to find c and s to zero the A[j][i] element
                val over = (A.matrixArray[j][i] / A.matrixArray[i][i])

                val c = Complex(
                    (Complex(1.0, 0.0) / (Complex(1.0, 0.0) + over * over)).real.sqrt(Constants.Preicion),
                    BigDecimal(0.0, Constants.Preicion)
                )
                var s = (Complex(
                    BigDecimal(1.0, Constants.Preicion).setScale(Constants.Scale, RoundingMode.HALF_UP),
                    BigDecimal(0.0, Constants.Preicion).setScale(Constants.Scale, RoundingMode.HALF_UP)
                ) - c * c).real.sqrt(Constants.Preicion)
                if (over.real.toDouble() < 0) s = s.negate()

                val newS = Complex(s, BigDecimal(0.0, Constants.Preicion))

                solveTask3(A, i, j, c, newS)
                solveTask3(orthogonal, i, j, c, newS)

                history.add(GivensRotation(i,j,c,newS))
            }
        }
    }
    return Solution(orthogonal.transpose(), A)
}