package lectures.part3oop

import playground.{Cinderella => Princess, PrinceCharming}

import java.util.Date
import java.sql.{Date => SQLDate}

object PackagingAndImports extends App {

  // package object
  sayHello
  PI

  // imports
  val asd = new PrinceCharming
  val cinderella = new Princess

  // 1. use fully qualified names
  val date = new Date
  val sqlDate = new java.sql.Date(10000)
  // 2. use alias
  val sqlDate2 = new SQLDate(1000)

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???

}
