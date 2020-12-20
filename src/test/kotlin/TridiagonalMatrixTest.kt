import Shifts.solveTask11
import Structures.Complex
import Structures.Matrix
import `Applying QR algorithm`.solveTask10
import `QR algorithm`.solveTask8
import `Tridiagonal matrix`.solveTask9
import org.junit.jupiter.api.Test
import java.lang.Math.abs
import java.math.BigDecimal

class TridiagonalMatrixTest {
    @Test
    fun `9 task test`() {
        val cols = 20
        val A = Matrix(cols, cols)
        for (i in 0 until cols) {
            for (j in i until cols) {
                A.matrixArray[i][j] = Complex(
                    BigDecimal(Constants.rand.nextDouble(), Constants.Preicion),
                    BigDecimal(0.0, Constants.Preicion)
                )
                A.matrixArray[i][j] = A.matrixArray[j][i]
            }
        }
        val answer = solveTask9(A)
        assert((answer.Q.transpose() * A * answer.Q - answer.newA).norm() <= Constants.Epsilon)
        for (i in 0 until A.rows) {
            for (j in 0 until A.cols) if (kotlin.math.abs(i - j) >= 2) assert(answer.newA.matrixArray[i][j].module() <= Constants.Epsilon)
        }
    }

    @Test
    fun `10 task test`() {
        val cols = 15
        val A = Matrix(cols, cols)
        for (i in 0 until cols) {
            for (j in i until cols) {
                if (kotlin.math.abs(i - j) >= 2) A.matrixArray[i][j] = Complex(
                    BigDecimal(0.0, Constants.Preicion),
                    BigDecimal(0.0, Constants.Preicion)
                )
                else A.matrixArray[i][j] = Complex(
                    BigDecimal(Constants.rand.nextDouble(), Constants.Preicion),
                    BigDecimal(0.0, Constants.Preicion)
                )
                A.matrixArray[j][i] = A.matrixArray[i][j]
            }
        }

        val answer = solveTask10(A, Constants.Epsilon)
        for (i in 0 until answer.Q.cols) {
            val eigen = Matrix(answer.Q.rows, 1)
            for (j in 0 until answer.Q.rows) {
                eigen.matrixArray[j][0] = answer.Q.matrixArray[j][i]
            }
            assert((A * eigen - eigen * answer.Ak[i]).norm() <= Constants.Epsilon)
        }
    }

    @Test
    fun `11 task test`() {
        val cols = 20
        val A = Matrix(cols, cols)
        for (i in 0 until cols) {
            for (j in i until cols) {
                if (kotlin.math.abs(i - j) >= 2) A.matrixArray[i][j] = Complex(
                    BigDecimal(0.0, Constants.Preicion),
                    BigDecimal(0.0, Constants.Preicion)
                )
                else A.matrixArray[i][j] = Complex(
                    BigDecimal(Constants.rand.nextDouble(), Constants.Preicion),
                    BigDecimal(0.0, Constants.Preicion)
                )
                A.matrixArray[j][i] = A.matrixArray[i][j]
            }
        }

        val answer = solveTask11(A, Constants.Epsilon)
        for (i in 0 until answer.Q.cols) {
            val eigen = Matrix(answer.Q.rows, 1)
            for (j in 0 until answer.Q.rows) {
                eigen.matrixArray[j][0] = answer.Q.matrixArray[j][i]
            }
            assert((A * eigen - eigen * answer.Ak[i]).norm() <= Constants.Epsilon)
        }
    }

}