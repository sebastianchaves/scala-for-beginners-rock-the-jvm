package lectures.part4fp

import scala.util.Random

object Options extends App {

  val myOption: Option[Int] = Some(1)
  val noOption: Option[Int] = None

  println(myOption)

  // unsafe APIs
  def unsafeMethod: String = null
  // val result = Some(unsafeMethod) // WRONG

  val result = Option(unsafeMethod)
  println(result)

  // chained methods
  def backupMethod: String = "safe u"
  val chainedResult = Option(unsafeMethod).orElse(Option(backupMethod))

  // DESIGN unsafe APIs
  def betterUnsafeMethod: Option[String] = None
  def betterBackupMethod: Option[String] = Option("Asd")

  val betterChainedResult = betterUnsafeMethod orElse betterBackupMethod

  // functions on options
  println(myOption.isEmpty)
  println(myOption.get) // UNSAFE - DO NOT USE THIS

  // map, flatMap, filter
  println(myOption.map(_ * 2))
  println(myOption.filter(_ % 2 == 0))
  println(myOption.flatMap(x => Option(x * 10)))

  // for-comprehensions
  /*
    Exercise
    1.
   */

  // fetched from elsewhere
  val config: Map[String, String] = Map("host" -> "127.0.0.1", "port" -> "80")

  class Connection {
    def connect: String = "Connected"
  }
  object Connection {
    val random = new Random(System.nanoTime)
    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean) Option(new Connection) else None
  }

  // try to establish connection, if so print connect method

  val connection = for {
    host        <- config.get("host")
    port        <- config.get("post")
    connection  <- Connection(host, port)
  } yield connection.connect

}
