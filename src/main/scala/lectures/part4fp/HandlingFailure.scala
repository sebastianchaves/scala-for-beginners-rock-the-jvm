package lectures.part4fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  // create success and failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("CTM"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod: Try[String] = Try(throw new RuntimeException("CSM"))

  val potentialFailure = Try(unsafeMethod)
  println(potentialFailure)

  // syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw
  }

  // utilities
  println(potentialFailure.isSuccess)

  // orElse
  def backupMethod: Try[String] = Try("valid string")
  val fallbackTry = unsafeMethod.orElse(backupMethod)

  // map, flatMap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))
  // for-comprehensions

  /*
    Exercise
   */
  val hostname = "localhost"
  val port = "8080"

  def renderHTML(page: String): Unit = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime)
      if (random.nextBoolean) "your page" else throw new RuntimeException("Invalid connection")
    }
  }

  object HttpService {
    val random = new Random(System.nanoTime)
    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean) new Connection
      else throw new RuntimeException("ERROR in Http Service")
    }
  }

  for {
    connection  <- Try(HttpService.getConnection(hostname, port))
    page        <- Try(connection.get("random URL"))
  } yield renderHTML(page)


}
