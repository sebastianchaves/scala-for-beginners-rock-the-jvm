package lectures.part4fp

import scala.util.Random

object Sequences extends App {

  // Seq
  val aSeq = Seq(1, 2, 3, 4)

  println(aSeq)
  println(aSeq.reverse)
  println(aSeq(2))
  println(aSeq ++ Seq(5, 6, 7))
  println(aSeq.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 until 10
  aRange.foreach(println)

  (1 to 10).foreach(x => println("hello"))

  // Lists
  val aList = List(1, 2, 3)
  val prepended = 42 +: aList :+ 43
  println(prepended)

  val apples5 = List.fill(5)("apple")
  println(apples5)
  println(aList.mkString("-"))

  // Arrays
  val numbers = Array(1,2,3)
  val arrayThree = Array.ofDim[String](3)
  println(arrayThree.mkString(" "))

  // mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2, 0)
  println(numbers.mkString(" "))

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers // implicit conversions
  println(numbersSeq) // WrappedArray(1, 2, 0)

  // Vectors
  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  // vectors vs lists
  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random

    val times = for {
      _ <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime
      collection.updated(r.nextInt(maxCapacity), r.nextInt)
      System.nanoTime - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  // advantage: keeps reference to tail
  // disadvantage: updating an element in the middle takes long
  val numbersList: List[Int] = (1 to maxCapacity).toList

  // advantage: depth of the tree is small
  // disadvantage (not too much): needs to replace an entire 32-element chunk
  val numbersVector: Vector[Int] = (1 to maxCapacity).toVector

  println(getWriteTime(numbersList))
  println(getWriteTime(numbersVector))

}
