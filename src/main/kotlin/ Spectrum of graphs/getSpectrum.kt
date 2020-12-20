package ` Spectrum of graphs`

import Shifts.solveTask11
import Structures.Complex
import Structures.Matrix
import `Tridiagonal matrix`.solveTask9

fun getSpectrum(A : Matrix) : MutableList<Complex> {
    val newMatrix = solveTask9(A).newA
    return solveTask11(newMatrix, Constants.Epsilon).Ak
}