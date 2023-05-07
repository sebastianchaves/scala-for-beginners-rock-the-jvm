package lectures.part3oop

import lectures.part3oop.Objects.Person.{N_EYES, canFly}

// Notes
/*
Scala doesnt have static values/methods

Scala objects
- Singleton pattern in one line
- are in their own class
- are the only instance

Companion objects
- can access each other's private members
- Scala is more OO than java

Scala applications
- objects that implements:
  `def main(args: Array[String]): Unit`
*/
object Objects extends App {

  class Person(val name: String) {
    // instance level functionality
  }
  object Person {
    // "static"/"class" level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    // factory method
    def apply(mother: Person, father: Person, name: String): Person = new Person(name)
  }

  println(Person.N_EYES)
  println(Person.canFly)

  val mary = Person
  val john = Person
  println(mary == john)

  val charly = new Person("charly")
  val bobby = new Person("bobby")
  println(charly == bobby)

}

object MyApp {
  def main(args: Array[String]): Unit = {
    println("My App")
  }
}
