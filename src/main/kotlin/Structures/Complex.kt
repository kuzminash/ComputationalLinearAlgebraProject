package Structures

import java.math.BigDecimal
import Constants
import java.math.RoundingMode
import kotlin.math.abs
import kotlin.math.sqrt

/*
We are using BigDecimal for the
greater accuracy
 */


data class Complex(val real: BigDecimal, val imaginary: BigDecimal) {

    constructor(real_: Double, imaginary_: Double) : this(
        real_.toBigDecimal().setScale(Constants.Scale, RoundingMode.UP),
        imaginary_.toBigDecimal().setScale(Constants.Scale, RoundingMode.UP)
    )


    fun inverse() : Complex {
        val s = (real * real + imaginary * imaginary).setScale(Constants.Scale, RoundingMode.UP)
        return Complex(real.divide(s,Constants.Preicion).setScale(Constants.Scale, RoundingMode.UP),
            -imaginary.divide(s,Constants.Preicion).setScale(Constants.Scale, RoundingMode.UP))
    }

    //override operator +
    operator fun plus(other: Complex): Complex {
        return Complex((real + other.real).setScale(Constants.Scale, RoundingMode.UP),
            (imaginary + other.imaginary).setScale(Constants.Scale, RoundingMode.UP))
    }

    //override operator -
    operator fun minus(other: Complex): Complex {
        return Complex((real - other.real).setScale(Constants.Scale, RoundingMode.UP),
            (imaginary - other.imaginary).setScale(Constants.Scale, RoundingMode.UP))
    }

    //override operator *
    operator fun times(other: Complex): Complex {
        return Complex(
            (real * other.real - imaginary * other.imaginary).setScale(Constants.Scale, RoundingMode.UP),
            (real * other.imaginary + imaginary * other.real).setScale(Constants.Scale, RoundingMode.UP))
    }

    //override operator *
    operator fun times(other: Double): Complex {
        return Complex(
            (real * other.toBigDecimal()).setScale(Constants.Scale, RoundingMode.UP),
            (imaginary * other.toBigDecimal()).setScale(Constants.Scale, RoundingMode.UP))
    }

    operator fun div(other: Complex): Complex {
        return this * other.inverse()
    }

    operator fun unaryMinus(): Complex {
        return Complex(-real, -imaginary)
    }

    //override operator toString -> a+bi
    override fun toString(): String {
        return "$real+$imaginary" + "i"
    }

    fun conjugate() : Complex {
        return Complex(real, -imaginary)
    }
    fun module(): Double {
        return (real * real + imaginary * imaginary).sqrt(Constants.Preicion).toDouble()
    }
}