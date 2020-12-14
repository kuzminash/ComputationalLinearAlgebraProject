import Structures.readMatrixFromFile
import `Iterative methods`.solveFixedPoint
import org.junit.jupiter.api.Test

class FixedPointTest() {

    fun help(matrixS: String, bS: String) {
        val matrix = readMatrixFromFile(matrixS)
        val b = readMatrixFromFile(bS)
        val answer = solveFixedPoint(matrix, b, Constants.Epsilon.toBigDecimal())
        assert((answer.matrix - matrix * answer.matrix - b).norm() <= Constants.Scale.toBigDecimal())
    }

    @Test
    fun firstTest() {
        help("src/test/resources/FixedPoint/matrix1",
            "src/test/resources/FixedPoint/b1")
    }

    @Test
    fun secondTest() {
        help("src/test/resources/FixedPoint/matrix2",
            "src/test/resources/FixedPoint/b2")
    }


}