package lectures.part4fp

object MapFlatmapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  def toPair: Int => List[Int] = x => List(x, x + 1)
  println(list.flatMap(toPair))

  // print all combinations between two lists
  val numbers = List(1, 2, 3, 4)
  val letters = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")

  val combinations = numbers.flatMap(n => letters.map(l => s"$n$l"))
  println(combinations)

  // for-comprehensions
  for {
    n <- numbers if n % 2 == 0
    l <- letters
    c <- colors
  } yield s"$n$l$c"

  // Exercises



}
