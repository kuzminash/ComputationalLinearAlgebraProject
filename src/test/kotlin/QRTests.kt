import Structures.*
import `QR algorithm`.solveTask7
import `QR algorithm`.solveTask8
import `QR decomposition`.`Givens rotation`.solveTask3
import `QR decomposition`.`Givens rotation`.solveTask4
import `QR decomposition`.`Householder transformation`.solveTask6
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.random.Random.Default.nextDouble

class QRTests {

    @Test
    fun `test task 4`() {
        val cols = 50
        val A = Matrix(cols, cols)
        for (i in 0 until cols) {
            for (j in 0 until cols) {
                A.matrixArray[i][j] = Complex(
                    BigDecimal(Constants.rand.nextDouble(), Constants.Preicion),
                    BigDecimal(0.0, Constants.Preicion)
                )
            }
        }
        val answer = solveTask4(A)
        for (i in 0 until cols) {
            for (j in 0 until cols) {
                if (i > j) assert(answer.R.matrixArray[i][j].module() < Constants.Epsilon)
            }
        }
        assert((answer.Q * answer.R - A).norm() <= Constants.Epsilon)
        assert((answer.Q * answer.Q.transpose() - createId(cols)).norm() <= Constants.Epsilon)
    }

    @Test
    fun `test task 6`() {
        val cols = 50
        val A = Matrix(cols, cols)
        for (i in 0 until cols) {
            for (j in 0 until cols) {
                A.matrixArray[i][j] = Complex(
                    BigDecimal(Constants.rand.nextDouble(), Constants.Preicion),
                    BigDecimal(0.0, Constants.Preicion)
                )
            }
        }
        val answer = solveTask6(A)
        for (i in 0 until cols) {
            for (j in 0 until cols) {
                if (i > j) assert(answer.R.matrixArray[i][j].module() < Constants.Epsilon)
            }
        }
        assert((answer.Q * answer.R - A).norm() <= Constants.Epsilon)
        assert((answer.Q * answer.Q.transpose() - createId(cols)).norm() <= Constants.Epsilon)
    }

    @Test
    fun `test task 7`() {
        val cols = 50
        val A = Matrix(cols, cols)
        for (i in 0 until cols) {
            for (j in 0 until cols) {
                A.matrixArray[i][j] = Complex(
                    BigDecimal(Constants.rand.nextDouble(), Constants.Preicion),
                    BigDecimal(0.0, Constants.Preicion)
                )
            }
        }
        val answer = solveTask7(A, Constants.Epsilon)
        assert((A * answer.m - answer.m * answer.c).norm() < Constants.Epsilon)
    }

    @Test
    fun `test QR algorithm`() {
        val cols = 50
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
        val answer = solveTask8(A, Constants.Epsilon)

        for (i in 0 until answer.Q.cols) {
            val eigen = Matrix(answer.Q.rows, 1)
            for (j in 0 until answer.Q.rows) {
                eigen.matrixArray[j][0] = answer.Q.matrixArray[j][i]
            }
            assert((A * eigen - eigen * answer.Ak[i]).norm() <= Constants.Epsilon)
        }
    }
}