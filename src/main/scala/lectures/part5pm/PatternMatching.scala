package lectures.part5pm

import scala.util.Random

object PatternMatching extends App {

  // switch on steroids
  val random = new Random
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "the ONE"
    case 2 => "double or nothing"
    case _ => "something else" // _ = wildcard
  }

  println(x)
  println(description)

  // 1. decompose values
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)

  val greeting = bob match {
    case Person(name, age) if age < 21 => s"Hi my name is $name and i cant drink in US"
    case Person(name, age) => s"Hi my name is $name and im $age years old"
    case _ => "NN"
  }

  println(greeting)

  /*
    1. cases are matched in order
    2. what if no cases match? MatchError
    3. type of the pattern matching expression? unified type of all the types in all the cases
    4. pm works really well with case classes
   */

  // pattern matching in sealed hierarchies
  sealed trait Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Hot dog")

  animal match {
    case Dog(breed) => println(s"Dog of breed $breed")
  }

  // Exercise
  sealed trait Expr
  case class Number(value: Int) extends Expr
  case class Sum(a: Expr, b: Expr) extends Expr
  case class Prod(a: Expr, b: Expr) extends Expr

  val complex = Prod(Sum(Number(1), Number(2)), Prod(Number(3), Sum(Number(5), Number(7))))

  def show(e: Expr): String = e match {
    case Number(value)  => s"$value"
    case Sum(a, b)      => s"${show(a)} + ${show(b)}"
    case Prod(a, b)     => {
      def maybeParentheses(exp: Expr): String = exp match {
        case Prod(_, _) => show(exp)
        case Number(_) => show(exp)
        case _ => s"(${show(exp)})"
      }
      s"(${maybeParentheses(a)} * ${maybeParentheses(b)})"
    }
  }

  println(show(complex))

}
