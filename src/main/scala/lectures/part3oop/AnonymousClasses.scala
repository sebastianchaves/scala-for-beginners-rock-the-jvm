package lectures.part3oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("jaja")
  }

  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"hello im $name")
  }

  val jim = new Person("asd") {
    override def sayHi: Unit = println(s"hello im jim")
  }
}
