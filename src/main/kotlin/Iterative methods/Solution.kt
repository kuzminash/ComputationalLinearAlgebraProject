package `Iterative methods`

import Structures.Matrix

data class Solution(val find: Int, val matrix: Matrix) {
    constructor(find_: Int) : this(find_, Matrix(1, 1))
}