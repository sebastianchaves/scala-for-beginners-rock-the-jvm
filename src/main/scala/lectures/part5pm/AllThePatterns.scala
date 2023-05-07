package lectures.part5pm

import exercises.{MyList, NonEmpty, Empty}

object AllThePatterns extends App {

  // 1 - constants
  val x: Any = "Scala"
  val constants = x match {
    case 1              => "a number"
    case "Scala"        => "THE Scala"
    case true           => "the truth"
    case AllThePatterns => "A singleton object"
  }

  // 2 - match anything
  // 2.1 wildcard
  val matchAnything = x match {
    case _ => ???
  }

  // 2.2 varible
  val matchingAVariable = x match {
    case something => s"ive found $something"
  }

  // 3 - tuples
  val tuple = (1, 2)
  val matchATuple = tuple match {
    case (1, 2)         => ???
    case (something, 2) => something
  }

  val nestedTuple = (1, (2, 3))
  val matchNestedTuple = nestedTuple match {
    case (_, (2, v)) => v
  }
  // pm can be nested

  // 4 - case classes - constructor pattern
  // pm can be nested with case classes as well
  val aList: MyList[Int] = NonEmpty(1, NonEmpty(2, Empty))
  val matchAList = aList match {
    case Empty =>
    case NonEmpty(head, NonEmpty(subhead, subtail)) =>
  }

  // 5 - list patterns
  val aStandardList = List(1, 2, 3, 4)
  val standardListMatching = aStandardList match {
    case List(1, _, _, _) => // extractor - advance
    case List(1, _*) => // list of arbitrary length - advance
    case 1 :: List(_) => // infix pattern
    case List(1, 2, 3) :+ 42 => // infix pattern
  }

  // 6 - type specifiers
  // WARNING: take care of type erasure of generic types
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => // explicit type specifier
    case _ =>
  }

  // 7 - name binding
  val nameBindingMatch = aList match {
    case nonEmpty @ NonEmpty(_, _) => // name binding => use the name later
    case NonEmpty(_, rest @ NonEmpty(head, tail)) => // name binding inside nested patterns
  }

  // 8 - multi patterns
  val multipattern = aList match {
    case Empty | NonEmpty(_, _) => // compound pattern
  }

  // 9 - if guards
  val secondElementSpecial = aList match {
    case NonEmpty(_, NonEmpty(elem, _)) if elem == 2 =>
  }

}
