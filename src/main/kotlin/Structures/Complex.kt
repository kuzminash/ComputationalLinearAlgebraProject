package Structures

import java.math.BigDecimal
import Constants
import kotlin.math.sqrt

/*
We are using BigDecimal for the
greater accuracy
 */


data class Complex(val real: BigDecimal, val imaginary: BigDecimal) {

    constructor(real_: Double, imaginary_: Double) : this(
        real_.toBigDecimal().setScale(Constants.Scale),
        imaginary_.toBigDecimal().setScale(Constants.Scale)
    )

    fun isEqual(other: Complex): Boolean {
        if (real == other.real && imaginary == other.imaginary) return true
        return false
    }

    //override operator +
    operator fun plus(other: Complex): Complex {
        return Complex(real + other.real, imaginary + other.imaginary)
    }

    //override operator -
    operator fun minus(other: Complex): Complex {
        return Complex(real - other.real, imaginary - other.imaginary)
    }

    //override operator *
    operator fun times(other: Complex): Complex {
        return Complex(
            real * other.real - imaginary * other.imaginary,
            real * other.imaginary + imaginary * other.real
        )
    }

    operator fun div(other: Complex): Complex {
        return (Complex((real * other.real + imaginary * other.imaginary) / (other.real * other.real + other.imaginary * other.imaginary),
            (other.real * imaginary - real * other.imaginary) / (other.real * other.real + other.imaginary * other.imaginary)))
    }

    //override operator toString -> a+bi
    override fun toString(): String {
        return "$real+$imaginary" + "i"
    }

    fun module(): Double {
        return sqrt((real * real + imaginary * imaginary).toDouble())
    }
}