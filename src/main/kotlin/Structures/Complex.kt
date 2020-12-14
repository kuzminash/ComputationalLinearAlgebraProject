package Structures

import java.math.BigDecimal
import Constants
import kotlin.math.abs
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


    fun inverse() : Complex {
        val s = (real * real + imaginary * imaginary).setScale(Constants.Scale)
        return Complex(real.divide(s).setScale(Constants.Scale), imaginary.divide(s).setScale(Constants.Scale))
    }

    //override operator +
    operator fun plus(other: Complex): Complex {
        return Complex(real + other.real, imaginary + other.imaginary)
    }

    //override operator -
    operator fun minus(other: Complex): Complex {
        return Complex((real - other.real).setScale(Constants.Scale),
            (imaginary - other.imaginary).setScale(Constants.Scale))
    }

    //override operator *
    operator fun times(other: Complex): Complex {
        return Complex(
            (real * other.real - imaginary * other.imaginary).setScale(Constants.Scale),
            (real * other.imaginary + imaginary * other.real).setScale(Constants.Scale))
    }

    operator fun div(other: Complex): Complex {
        return this * other.inverse()
    }

    //override operator toString -> a+bi
    override fun toString(): String {
        return "$real+$imaginary" + "i"
    }

    fun module(): Double {
        return sqrt((real * real + imaginary * imaginary).toDouble())
    }
}