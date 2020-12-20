package Structures

import Structures.Complex
import java.io.File
import java.math.BigDecimal
import Constants
import java.math.RoundingMode
import kotlin.math.abs
import kotlin.math.sqrt


data class Matrix(val matrixArray: MutableList<MutableList<Complex>>) {

    /*
    indexing from 0
     */


    constructor(rows_: Int, cols_: Int) : this(
        (MutableList(rows_)
        { MutableList(cols_) { Complex(0.0, 0.0) } })
    )


    val rows = matrixArray.size
    val cols = matrixArray[0].size
    fun copy(): Matrix {
        val result = Matrix(rows, cols)
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                result.matrixArray[i][j] = matrixArray[i][j]
            }
        }
        return result
    }


    fun printMatrix() {
        matrixArray.forEach { println(it.joinToString(" ")) }
    }

    fun isEqual(other: Matrix): Boolean {
        if (cols != other.cols || rows != other.rows) return false
        if ((this - other).norm() > Constants.Epsilon) return false
        return true
    }

    //overload operator +
    operator fun plus(other: Matrix): Matrix {
        //check if the matrix size is valid
        assert(rows == other.rows && cols == other.cols)

        val newMatrix = Matrix(rows, cols)

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                newMatrix.matrixArray[i][j] = matrixArray[i][j] + other.matrixArray[i][j]
            }
        }
        return newMatrix
    }

    //overload operator -
    operator fun minus(other: Matrix): Matrix {
        assert(rows == other.rows && cols == other.cols)

        val newMatrix = Matrix(rows, cols)

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                newMatrix.matrixArray[i][j] = matrixArray[i][j] - other.matrixArray[i][j]
            }
        }
        return newMatrix
    }

    //overload operator * (matrix * matrix)
    operator fun times(other: Matrix): Matrix {
        //check if the matrix size is valid
        assert(cols == other.rows)

        val newMatrix = Matrix(rows, other.cols)

        for (i in 0 until rows) {
            for (j in 0 until other.cols) {
                for (k in 0 until cols) {
                    newMatrix.matrixArray[i][j] += matrixArray[i][k] * other.matrixArray[k][j]
                }
            }
        }
        return newMatrix
    }

    //overload operator * (matrix * real)
    operator fun times(number: BigDecimal): Matrix {
        val n = Complex(number, 0.0.toBigDecimal().setScale(Constants.Scale, RoundingMode.UP))
        val newMatrix = Matrix(rows, cols)
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                newMatrix.matrixArray[i][j] = matrixArray[i][j] * n
            }
        }
        return newMatrix
    }

    //overload operator * (matrix * complex)
    operator fun times(number: Complex): Matrix {
        val newMatrix = Matrix(rows, cols)
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                newMatrix.matrixArray[i][j] = matrixArray[i][j] * number
            }
        }
        return newMatrix
    }

    //overload operator * (matrix * complex)
    operator fun times(number: Double): Matrix {
        val newMatrix = Matrix(rows, cols)
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                newMatrix.matrixArray[i][j] = matrixArray[i][j] * number
            }
        }
        return newMatrix
    }

    fun transpose(): Matrix {
        val newMatrix = Matrix(cols, rows)
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                newMatrix.matrixArray[j][i] = matrixArray[i][j]
            }
        }
        return newMatrix
    }

    operator fun div(other: BigDecimal): Matrix {
        return this * (((1.0).toBigDecimal().divide(other, Constants.Preicion)).setScale(
            Constants.Scale,
            RoundingMode.UP
        ))
    }

    fun norm(): Double {
        var n = 0.0
        for (i in 0 until rows) {
            for (j in 0 until cols) n += matrixArray[i][j].module() * matrixArray[i][j].module()
        }
        return sqrt(n)
    }

    fun conjugate(): Matrix {
        var newMatrix = this.copy()
        newMatrix = newMatrix.transpose()
        for (i in 0 until newMatrix.rows) {
            for (j in 0 until newMatrix.cols) newMatrix.matrixArray[i][j] = newMatrix.matrixArray[i][j].conjugate()
        }
        return newMatrix
    }

}


/*
in the first line of the file is written
amount of rows and columns

in the other |rows| lines
is written the rows of matrix

function is made for testing
 */

fun readMatrixFromFile(filePath: String): Matrix {

    val lines = File(filePath).readLines()
    val matrixData = lines[0].split(' ').map { it.toInt() }

    val rows = matrixData[0]
    val cols = matrixData[1]
    val newMatrix = Matrix(rows, cols)

    for (i in 0 until rows) {
        val newRow = lines[i + 1].split(' ').map { it.toDouble() }
        for (j in 0 until cols) {
            newMatrix.matrixArray[i][j] = Complex(newRow[j], 0.0)
        }
    }
    return newMatrix
}

fun createE(n: Int): Matrix {
    val newMarix = Matrix(n, n)
    for (i in 0 until newMarix.matrixArray.size) {
        newMarix.matrixArray[i][i] = Complex(1.0, 0.0)
    }
    return newMarix
}

fun createId(n: Int): Matrix {
    val matrix = Matrix(n, n)
    for (i in 0 until n) {
        matrix.matrixArray[i][i] = Complex(1.0, 0.0)
    }
    return matrix
}

fun createOne(r: Int, c: Int): Matrix {
    val matrix = Matrix(r, c)
    for (i in 0 until r) {
        for (j in 0 until c) matrix.matrixArray[i][j] = Complex(1.0, 0.0)
    }
    return matrix
}

fun findClosest(A: Matrix, i: Int): BigDecimal {
    val newMatrix = Matrix(2, 2)
    newMatrix.matrixArray[0][0] = A.matrixArray[i - 1][i - 1]
    newMatrix.matrixArray[0][1] = A.matrixArray[i - 1][i]
    newMatrix.matrixArray[1][0] = A.matrixArray[i][i - 1]
    newMatrix.matrixArray[1][1] = A.matrixArray[i][i]
    val a = Complex(1.0, 0.0).real
    val b = -(newMatrix.matrixArray[0][0].real + newMatrix.matrixArray[1][1].real)
    val c = -(newMatrix.matrixArray[1][0].real * newMatrix.matrixArray[0][1].real) +
            (newMatrix.matrixArray[0][0].real * newMatrix.matrixArray[1][1].real)

    val D = b * b - a * c * 4.0.toBigDecimal()
    val x1 = (-b + D.sqrt(Constants.Preicion)) / (a * 2.0.toBigDecimal())
    val x2 = (-b - D.sqrt(Constants.Preicion)) / (a * 2.0.toBigDecimal())
    if ((x1 - newMatrix.matrixArray[1][1].real).abs() > (x2 - newMatrix.matrixArray[1][1].real).abs()) return x2
    else return x1
}