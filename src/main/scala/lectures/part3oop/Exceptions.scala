package lectures.part3oop

object Exceptions extends App {

  // throwable classes extend Throwable class
  // Exception and Error are the major subtypes
  // Exception = something went wrong in the code
  // Error = something went wrong with the system

  // 2. catch exceptions
  def getInt(withException: Boolean): Int =
    if (withException) throw new RuntimeException("No int") else 43

  val potentialFail =
    try {
      getInt(false)
    } catch {
      case e: RuntimeException => 42
    } finally {
      // code that execute no matter what
      // optional
      // does not influence the return type of this expression
      // use finally only for side effects
      println("finally")
    }

  // 3. define exceptions
  class MyException extends Exception
  val exception = new MyException
//  throw exception

  /*
    1- Crash program with out of memory error
    2- Crash with stack overflow error
    3- Pocket calculator
      - add
      - subtract
      - multiply
      - divide

      throw custom exception
      - OverflowException if add(x, y) exceeds Int.MAX_VALUE
      - UnderflowException if subtract(x, y) exceeds Int.MIN_VALUE
      - MathCalculationException for division by 0
   */

//  throw new OutOfMemoryError("Out of memory")
//  val array = Array.ofDim(Int.MaxValue)

//  throw new StackOverflowError("Stack overflow")
//  def infinite: Int = 1 + infinite
//  val asd = infinite

  class OverflowException extends Exception
  class UnderflowException extends Exception
  class MathCalculationException(message: String) extends Exception(message)

  object MyCalculator {
    def add(x: Int, y: Int): Int = {
      val result = x + y

      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int): Int = {
      val result = x - y

      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int): Int = {
      val result = x * y

      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int): Int =
      if (y == 0) throw new MathCalculationException("Cannot divide by 0")
      else x / y
  }

  println(MyCalculator.add(2147483647, 2147483647))

}
