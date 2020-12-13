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
        val answer = solveGaussSeidel(matrix, b)
        assert((matrix * answer.matrix - b).norm() <= Constants.Scale.toBigDecimal())
    }

    @Test
    fun firstTest() {
        help("src/test/resources/GaussSeidel/matrix1",
            "src/test/resources/GaussSeidel/b1")
    }


    /*
    generate random matrix and vector
     */
    @Test
    fun secondTest() {
        var find = Solution(0)
        do {
            val matrix = Matrix(2, 2)
            matrix.matrixArray[0][0] = Complex(Random.nextInt().toDouble(), 0.0)
            matrix.matrixArray[0][1] = Complex(Random.nextInt().toDouble(), 0.0)
            matrix.matrixArray[1][0] = Complex(Random.nextInt().toDouble(), 0.0)
            matrix.matrixArray[1][1] = Complex(Random.nextInt().toDouble(), 0.0)
            val b = Matrix(2, 1)
            b.matrixArray[0][0] = Complex(Random.nextInt().toDouble(), 0.0)
            b.matrixArray[1][0] = Complex(Random.nextInt().toDouble(), 0.0)
            find = solveGaussSeidel(matrix, b)

        } while(find.find == 0)
    }

}