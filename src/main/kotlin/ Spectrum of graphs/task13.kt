package ` Spectrum of graphs`

import Structures.Complex
import Structures.Matrix
import java.math.BigDecimal
import java.math.RoundingMode

fun solveTask13First(n: Int): BigDecimal {

    val graph = Matrix(n * n, n * n)
    for (i in 0 until n * n) {
        val a = i / n
        val b = i % n
        /*
        finding where edges leave
        took the idea of how it's better to write
         */
        val to = mutableListOf(
            Pair((a + 2 * b) % n, b),
            Pair((a - 2 * b + 4 * n) % n, b),
            Pair((a + (2 * b + 1)) % n, b),
            Pair((a - (2 * b + 1) + 4 * n) % n, b),
            Pair(a, (b + 2 * a) % n),
            Pair(a, (b - 2 * a + 4 * n) % n),
            Pair(a, (b + (2 * a + 1)) % n),
            Pair(a, (b - (2 * a + 1) + 4 * n) % n)
        )
        for (j in to) graph.matrixArray[j.first * n + j.second][i] =
            graph.matrixArray[j.first * n + j.second][i] + Complex(1.0, 0.0)
    }

    val sGraph = getSpectrum(graph).sortedByDescending { it.real }
    return (sGraph[1].real.abs(Constants.Preicion)
        .max(sGraph[sGraph.size - 1].real.abs(Constants.Preicion))) / BigDecimal(8.0)

}

fun isPrime(n: Int): Boolean {
    for (i in 2 until n) {
        if (n % i == 0) return false
    }
    return true
}

fun binaryPow(n: Int, t: Int, mod: Int): Int {
    if (t == 0) return 1
    if (t % 2 == 0) {
        val fromRecursion = binaryPow(n, t / 2, mod)
        return (fromRecursion * fromRecursion) % mod
    } else {
        val fromRecursion = binaryPow(n, t - 1, mod)
        return (n * fromRecursion) % mod
    }
}

fun getInverseModulo(n: Int, mod: Int): Int {
    if (n == 0) return mod
    else if (n == mod) return 0
    else {
        return binaryPow(n, mod - 2, mod)
    }
}

fun solveTask13Second(n: Int) :BigDecimal{
    val graph = Matrix(n + 1, n + 1)
    for (i in 0 until n) {
        graph.matrixArray[(i - 1 + n) % n][i] += Complex(1.0, 0.0)
        graph.matrixArray[(i + 1) % n][i] += Complex(1.0, 0.0)
        graph.matrixArray[getInverseModulo(i, n)][i] += Complex(1.0, 0.0)
    }
    graph.matrixArray[0][n] = Complex(1.0, 0.0)
    graph.matrixArray[n][n] = Complex(2.0, 0.0)

    val sGraph = getSpectrum(graph).sortedByDescending { it.real }
    return (sGraph[1].real.abs(Constants.Preicion)
        .max(sGraph[sGraph.size - 1].real.abs(Constants.Preicion))) / BigDecimal(3.0)
}