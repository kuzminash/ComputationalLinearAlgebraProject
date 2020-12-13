package Structures

import Structures.Complex
import java.io.File
import java.math.BigDecimal
import Constants
import kotlin.math.sqrt


class Matrix(val matrixArray: MutableList<MutableList<Complex>>) {

    /*
    indexing from 0
     */

    constructor(rows_: Int, cols_: Int) : this(
        (MutableList(rows_)
        { MutableList(cols_) { Complex(0.0, 0.0) } })
    )

    val rows = matrixArray.size
    val cols = matrixArray[0].size


    fun printMatrix() {
        matrixArray.forEach { println(it.joinToString(" ")) }
    }

    fun isEqual(other: Matrix): Boolean {
        if (cols != other.cols || rows != other.rows) return false
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (matrixArray[i][j] != other.matrixArray[i][j]) return false
            }
        }
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
    operator fun times(number: BigDecimal) : Matrix {
        val n = Complex(number, 0.0.toBigDecimal().setScale(Constants.Scale))
        val newMatrix = Matrix(rows, cols)
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                newMatrix.matrixArray[i][j] = matrixArray[i][j] * n
            }
        }
        return newMatrix
    }

    //overload operator * (matrix * complex)
    operator fun times(number: Complex) {
        val newMatrix = Matrix(rows, cols)
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                newMatrix.matrixArray[i][j] = matrixArray[i][j] * number
            }
        }
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

    fun norm(): BigDecimal {
        var n = 0.0
        for (i in 0 until rows) {
            for (j in 0 until cols) n += matrixArray[i][j].module() * matrixArray[i][j].module()
        }
        return sqrt(n).toBigDecimal().setScale(Constants.Scale)
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

fun createOne(r: Int, c: Int) : Matrix {
    val matrix = Matrix (r, c)
    for (i in 0 until r) {
        for (j in 0 until c) matrix.matrixArray[i][j] = Complex(1.0, 0.0)
    }
    return matrix
}