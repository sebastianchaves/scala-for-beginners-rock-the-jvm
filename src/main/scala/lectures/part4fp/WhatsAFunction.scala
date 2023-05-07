package lectures.part4fp

object WhatsAFunction extends App {

  // Dream: use functions as first class elements
  // problem => OOP

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  println(doubler(2))

  // function types = Function1[A,B] etc..
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }
  println(stringToIntConverter("3" + 4))

  val adder = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  // function type Function2[A, B, R] = ((A, B) => R)
  // All scala functions are objects!

  /*
    1. function that take 2 strings and concatenate them
    2. transform MyPredicate
    3. define a function which takes an int and return a function which take an int and return an int
   */

  // high order functions = receive functions as parameters or return functions as result

  // 1.
  def concatenate: (String, String) => String  = (x, y) => x + y
  println(concatenate("hola", "carlos"))

  // 3.
  def curryFunction: Int => Int => Int = (v1: Int) => (v2: Int) => v1 + v2

  println(curryFunction(4)(5))

}

trait MyFunction[A, B] {
  def apply(element: A): B
}