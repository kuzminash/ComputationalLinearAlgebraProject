package `QR decomposition`.`Householder transformation`

import Structures.Complex
import Structures.Matrix
import Structures.createId
import `QR decomposition`.`Givens rotation`.Solution

fun solveTask6(a : Matrix) : Solution{

    var A = a.copy()
    var orthogonal = createId(A.cols)

    for (i in 0 until A.cols - 1) {

        val newVector = Matrix(A.rows, 1)
        val vec1 = Matrix(A.cols - i, 1)
        val vec2 = Matrix(A.cols - i, 1)

        vec2.matrixArray[0][0] = Complex(1.0, 0.0)

        for (j in 0 until A.cols - i) vec1.matrixArray[j][0] = A.matrixArray[j + i][i]

        if (vec1.norm() <= Constants.Epsilon) continue
        val normVec1 = vec1 * (1.0 / vec1.norm())
        if ((normVec1 - vec2).norm() <= Constants.Epsilon) continue
        val vec3 = (normVec1 - vec2) * (1.0 / ((normVec1 - vec2).norm()))
        for (j in i until A.rows) newVector.matrixArray[j][0] = vec3.matrixArray[j - i][0]


        A = solveTask5(A, newVector)
        orthogonal = solveTask5(orthogonal, newVector)

    }

    return Solution(orthogonal.transpose(), A)
}