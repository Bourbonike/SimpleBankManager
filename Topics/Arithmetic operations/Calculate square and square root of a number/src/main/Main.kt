import kotlin.math.pow
import kotlin.math.sqrt

fun main(args: Array<String>) {
    // Reading the integer from user
    val input = readln().toInt()

    // Perform the arithmetic operations on 'input' here
    val square = input.toDouble().pow(2).toInt()
    val root = sqrt(input.toDouble())

    // Prepare the output in required format and print it
    val result = "Square: $square, Square root: $root"
    println(result)
}