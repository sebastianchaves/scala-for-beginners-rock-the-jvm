package lectures.part3oop

object AbstractDataType extends App {

  // Abstract
  abstract class Animal {
    val creatureType: String = "wild"
    def eat(): Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"
    def eat(): Unit = println("dog eating")
  }

  // Trait
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "fresh meat"
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"
    def eat(): Unit = println("croco eating")
    def eat(animal: Animal): Unit = println(s"croc eating a ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile

  croc.eat(dog)

  // traits vs abstract classes
  // 1 - traits do not have constructor parameters
  // 2 - multiple traits may be inherited by the same class
  // 3 - traits = behavior (what do) (lo que hace), abstract class = "things" (what is) (lo que es)

}
