import java.math.MathContext
import kotlin.random.Random

object Constants {
    const val Scale = 50
    const val Epsilon = 0.0001
    const val smallerEpsilon = 0.00000001
    val Preicion = MathContext(50)
    val rand = Random(System.currentTimeMillis())
}