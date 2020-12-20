package ` Spectrum of graphs`

import Structures.Matrix

fun solveTask12(G1 : Matrix, G2 : Matrix) : Boolean {
    val sG1 = getSpectrum(G1).sortedBy { it.real }
    val sG2 = getSpectrum(G2).sortedBy { it.real }
    if (sG1.size != sG2.size) return false
    for (i in 0 until  sG1.size) {
        if (sG1[i] != sG2[i]) return false
    }
    return true
}