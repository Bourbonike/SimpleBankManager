import java.lang.NumberFormatException


fun main(args: Array<String>) {
    val strNumber = readLine()!!
    try {
        strNumber.toInt()
        println("Operation successful")
    }catch (e:NumberFormatException){
        println("Operation failed: Non-numeric string")
    }

    // Write your code here to parse the strNumber to an integer.
    // Use a try-catch block.
    // If it is successful, print 'Operation successful'.
    // If a NumberFormatException is thrown, print 'Operation failed: Non-numeric string'.
    // For any other exceptions that might occur, print 'Operation failed: Unexpected error'.
}