package exercises

trait MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](elem: B): MyList[B]
  def elementsToString: String
  override def toString: String = s"[$elementsToString]"

  // High order functions
  def map[B](transformer: A => B): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  def foreach(f: A => Unit): Unit
  def sort(f: (A, A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]
  def fold[B](start: B, operator: (B, A) => B): B
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](elem: B): MyList[B] = NonEmpty(elem, Empty)
  def elementsToString: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = Empty
  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  def foreach(f: Nothing => Unit): Unit = ()

  def sort(f: (Nothing, Nothing) => Int): MyList[Nothing] = Empty

  def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("lists do not have the same length")
    else Empty

  def fold[B](start: B, operator: (B, Nothing) => B): B = start
}

case class NonEmpty[+A](head: A,  tail: MyList[A]) extends MyList[A] {
  def isEmpty: Boolean = false
  def add[B >: A](elem: B): MyList[B] = NonEmpty(elem, this)
  def elementsToString: String = if (tail.isEmpty) s"$head" else s"$head,${tail.elementsToString}"

  def map[B](transformer: A => B): MyList[B] =
    NonEmpty(transformer(head), tail.map(transformer(_)))

  def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(head)) NonEmpty(head, tail.filter(predicate)) else tail.filter(predicate)

  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    NonEmpty[B](transformer(head).head, tail.flatMap(transformer(_)))

  def foreach(f: A => Unit): Unit = {
    f(head)
    tail.foreach(f)
  }

  def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] =
      if (sortedList.isEmpty) new NonEmpty[A](x, Empty)
      else if (compare(x, sortedList.head) <= 0) new NonEmpty[A](x, sortedList)
      else new NonEmpty[A](sortedList.head, insert(x, sortedList.tail))

    val sortedTail = tail.sort(compare)

    insert(head, sortedTail)
  }

  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] =
    if (list.isEmpty) throw new RuntimeException("lists do not have the same length")
    else new NonEmpty[C](zip(head, list.head), tail.zipWith(list.tail, zip))

  def fold[B](start: B, operator: (B, A) => B): B = {
    tail.fold(operator(start, head), operator)
  }
}

object ListTest extends App {
  val listOfIntegers: MyList[Int] = NonEmpty(1, NonEmpty(1, Empty))
  val listOfStrings: MyList[String] = NonEmpty("asd", NonEmpty("asd", Empty))
//  println(listOfIntegers.toString)
//  println(listOfStrings.toString)
//
//  println(listOfIntegers.map((elem: Int) => elem + 1))

  listOfIntegers.foreach(println)
  listOfIntegers.sort((x, y) => y - x)
  println(listOfIntegers.zipWith(listOfStrings, (x: Int, y: String) => x + y))

  println(listOfIntegers.fold[Int](0, _ + _))

  val combinations = for {
    n <- listOfIntegers
    s <- listOfStrings
  } yield s"$n-$s"

  println(combinations)
}
