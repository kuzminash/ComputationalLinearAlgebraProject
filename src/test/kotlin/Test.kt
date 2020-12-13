import Structures.Matrix
import Structures.readMatrixFromFile
import `Iterative methods`.easyGauss
import org.junit.jupiter.api.Test

class TestBasicFunctions {
    @Test
    fun testSum() {
        val matrix1 = readMatrixFromFile("src/test/resources/matrix1")
        val matrix2 = readMatrixFromFile("src/test/resources/matrix2")

        val resMatrix = matrix1 + matrix2
        val correct = readMatrixFromFile("src/test/resources/sumMatrix1Matrix2")
        assert(correct.isEqual(resMatrix))
    }
    @Test
    fun testMultiply() {
        val matrix1 = readMatrixFromFile("src/test/resources/matrix1")
        val matrix3 = readMatrixFromFile("src/test/resources/matrix3")

        val resMatrix = matrix1 * matrix3
        val correct = readMatrixFromFile("src/test/resources/multMatrix1Matrix2")
        correct.isEqual(resMatrix)
    }
}