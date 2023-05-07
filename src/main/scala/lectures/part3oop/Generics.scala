package lectures.part3oop

object Generics extends App {

  class MyList[+A] {
    def add[B >: A](element: B): MyList[B] = ???
    /*
      A = Cat
      B = Animal
     */
  }

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // if B extends A, should List[B] extend List[A] ?
  // 1. yes, List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat] // Cats are animals

  // 2. no, List[Cat] != List[Animal] = INVARIANCE
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. CONTRAVARIANCE
  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal] // no sense

  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal] // make sense

  // bounded types
  class Cage[A <: Animal](animal: A)
  val cage = new Cage(new Dog)

  // expand MyList to be generic


}
