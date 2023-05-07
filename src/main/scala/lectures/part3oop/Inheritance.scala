package lectures.part3oop

object Inheritance extends App {

  // Single class inheritance
  class Animal {
    val `type` = "wild"
    def eat(): Unit = println("eat")
    protected def run(): Unit = println("run")
  }

  class Cat extends Animal {
    def runFaster(): Unit = {
      run()
      println("and faster")
    }
  }

  val cat = new Cat

  // Constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  // overriding
  class Dog(override val `type`: String) extends Animal {
    override def eat(): Unit = println("baba")
  }

  val dog: Dog = new Dog("domestic")
  dog.eat()

  // type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("k9")
  unknownAnimal.eat()

  // overriding != overloading

  // super

  // preventing override
  // 1- use final on method
  // 2- use final on the class
  // 3- seal the class = extends classes in this file, prevent extension in other file and gain exhaustive lookup

}
