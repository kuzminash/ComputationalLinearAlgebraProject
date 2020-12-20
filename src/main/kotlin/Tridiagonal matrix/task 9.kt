package `Tridiagonal matrix`

import Structures.Complex
import Structures.Matrix
import Structures.createId
import `QR decomposition`.`Householder transformation`.solveTask5
import `QR decomposition`.`Householder transformation`.solveTask5ButFromRight

fun solveTask9(a: Matrix): Solution4 {

    var A = a.copy()
    var orthogonal = createId(A.rows)

    for (i in 0 until A.cols - 2) {

        val vec1 = Matrix(A.rows - i - 1, 1)
        val vec2 = Matrix(A.rows - i - 1, 1)
        vec1.matrixArray[0][0] = Complex(1.0, 0.0)

        for (j in i + 1 until A.rows) vec2.matrixArray[j - i - 1][0] = A.matrixArray[j][i]
        if (vec2.norm() <= Constants.Epsilon) continue

        val normVec2 = vec2 * (1.0 / vec2.norm())
        val d = normVec2 - vec1

        if (d.norm() <= Constants.Epsilon) continue

        val normD = d * (1.0 / d.norm())
        val over = Matrix(A.rows, 1)
        for (j in i + 1 until A.rows) over.matrixArray[j][0] = normD.matrixArray[j - i - 1][0]
        A = solveTask5(solveTask5ButFromRight(A, over), over)
        orthogonal = solveTask5(orthogonal, over)
    }
    return Solution4(A, orthogonal.transpose())
}