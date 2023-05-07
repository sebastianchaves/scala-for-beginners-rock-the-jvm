package lectures.part3oop

object CaseClasses extends App {

  /*
    equals, hashCode, toString
   */
  case class Person(name: String, age: Int)

  // 1. class parameters are fields
  val carlos = Person("carlos", 30)
  println(carlos.name)

  // 2. sensible toString
  // println(instance) = println(instance.toString) // synctactic sugar
  println(carlos.toString)

  // 3. equals and hashCode implemented
  val carlos2 = Person("carlos", 30)
  println(carlos == carlos2)

  // 4. case classes have copy method
  val carlos3 = carlos.copy(age = 40)
  println(carlos3)
  println(carlos == carlos3)

  // 5. case classes have companion objects
  val thePerson = Person
  val maria = Person("maria", 70)

  // 6. case classes are serializable
  // Akka

  // 7. case classes have extractor patterns = case classes can be used in PATTERN MATCHING

  // 8. Case Object, the only different is that case object dont have companion object
  case object RepublicaArgentina {
    def name: String = "La Republica Argentina"
  }

  /*
    Expand MyList with case classes and case object
   */

}
