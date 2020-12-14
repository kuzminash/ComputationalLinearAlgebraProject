import Structures.Complex
import Structures.Matrix
import Structures.readMatrixFromFile
import `Iterative methods`.Solution
import `Iterative methods`.solveFixedPoint
import `Iterative methods`.solveGaussSeidel
import org.junit.jupiter.api.Test
import kotlin.random.Random

class GaussSeidelTest {

    fun help(matrixS: String, bS: String) {
        val matrix = readMatrixFromFile(matrixS)
        val b = readMatrixFromFile(bS)
        val answer = solveGaussSeidel(matrix, b, Constants.Epsilon.toBigDecimal())
        assert((matrix * answer.matrix - b).norm() <= Constants.Scale.toBigDecimal())
    }

    @Test
    fun firstTest() {
        help("src/test/resources/GaussSeidel/matrix1",
            "src/test/resources/GaussSeidel/b1")
    }


    @Test
    fun secondTest() {
        help("src/test/resources/GaussSeidel/matrix2",
            "src/test/resources/GaussSeidel/b2")
    }

}