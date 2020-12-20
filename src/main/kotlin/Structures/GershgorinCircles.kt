package Structures

import java.math.BigDecimal

class GershgorinCircles(val matrix: Matrix) {
    val circles = MutableList(matrix.cols) { Circle(Complex(0.0, 0.0), 0.0) }

    init {
        for (i in 0 until matrix.rows) {
            val c = matrix.matrixArray[i][i]
            val r = matrix.matrixArray[i].fold(0.0) { r, elem -> r + elem.module() }
            circles[i] = Circle(c, r - c.module())
        }
    }

    fun checkRadii(epsilon: Double): Boolean {
        return circles.all { it.radius < epsilon }
    }


    //eigenvalue estimate
    fun liesIn(): Boolean {
        for (i in 0 until matrix.rows) {
            val r = matrix.matrixArray[i].fold(0.0) { r, elem -> r + elem.module() }
            if (r >= 1) return false
        }
        return true
    }
}