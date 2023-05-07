package lectures.part4fp

object AnonymousFunctions extends App {

  // lambda = anonymous function
  val doubler: Int => Int = (x: Int) => x * 2

  // multiple parameters in a lambda
  val adder: (Int, Int) => Int = (x, y) => x + y

  // no parameters
  val doSomething: () => Int = () => 3

  // careful
  println(doSomething) // the function itself
  println(doSomething()) // the call of that function

  // curly braces with lambdas
  val stringToInt: String => Int = { (s: String) =>
    s.toInt
  }

  // MOAR syntactic sugar
  val increment: Int => Int = _ + 1
  val megaAdder: (Int, Int) => Int = _ + _

}
