package lectures.part4fp

object HOFsCurries extends App {

//  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = ???
  // higher order function (HOF)
  // pass functions as parameters
  // return functions as results

  // map, flatMap, filter, etc

  // function that applies a function n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x)))

  def nTimes(f: Int => Int, n: Int): Int => Int =
      if (n <= 0) (x: Int) => x
      else (x: Int) => nTimes(f, n - 1)(f(x))

  val plusOne: Int => Int = _ + 1

  val plusTen = nTimes(plusOne, 10)
  println(plusTen(1))

  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3)
  println(add3(10))

  // curry = functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: Double => String = curriedFormatter("%4.2f")
  val preciseFormat: Double => String = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  /*
    1.  Expand MyList
      - foreach method A => Unit
        [1,2,3].foreach(x => println(x))

      - sort function ((A, A) => Int) => MyList
        [1,2,3].sort((x, y) => y - x) => [3, 2, 1]

      - zipWith (list, (A, A)=> B) => MyList[B]
        [1,2,3].zipWith([4,5,6], x * y) => [1 * 4, 2 * 5, 3 * 6] = [4, 10, 18]

      - fold(start)(function) => a value
        [1,2,3].fold(0)(x + y) = 6

    2.  toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
        fromCurry(f: (Int => Int => Int)) => (Int => Int) => Int

    3.  compose(f, g) => f(g(x))
        andThen(f, g) => g(f(x))
   */

  def toCurry[A, B, C](f: (A, B) => C): A => B => C =
    x => y => f(x, y)

  def fromCurry[A, B, C](f: A => B => C): (A, B) => C =
    (x, y) => f(x)(y)

  def compose[A, B, C](f: A => B, g: C => A): C => B =
    x => f(g(x))

  def andThen[A, B, C](f: A => B, g: B => C): A => C =
    x => g(f(x))

  val superAdder2: (Int => Int => Int) = toCurry(_ + _)
  def add4: Int => Int = superAdder2(4)
  println(add4(17))

  val simplerAdder: (Int, Int) => Int = fromCurry(superAdder2)
  println(simplerAdder(4, 17))

  val add2: Int => Int = _ + 2
  val times3: Int => Int = _ * 3

  val composed = compose(add2, times3)
  val ordered = andThen(add2, times3)

  println(composed(4))
  println(ordered(4))

}
