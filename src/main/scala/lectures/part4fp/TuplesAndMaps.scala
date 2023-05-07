package lectures.part4fp

import scala.annotation.tailrec

object TuplesAndMaps extends App {

  // tuples = finite ordered "lists"
  val aTuple: (Int, String) = (2, "hi")

  println(aTuple._1)
  println(aTuple.copy(_2 = "goodbye"))
  println(aTuple.swap)

  // Maps = associates keys to values
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Carlos", 111), "Ricardo" -> 222)
  println(phonebook)

  // Map ops
  println(phonebook.contains("Carlos"))
  println(phonebook("Carlos")) // could crash

  val newPhone: (String, Int) = "Roberto" -> 333
  val phonebookUpdated = phonebook + newPhone
  println(phonebookUpdated)

  // functional on maps
  println(phonebookUpdated.map(phone => phone._1.toLowerCase -> phone._2))

  // filterKeys
  println(phonebookUpdated.view.filterKeys(p => p.startsWith("R")).toMap)

  // mapValues
  println(phonebookUpdated.view.mapValues(number => number * 10).toMap)

  // conversions to other collections
  println(phonebook.toList)
  println(List(("Romina", 444)).toMap)

  val names = List("Lucas", "Maria", "Manuel", "Ana")
  println(names.groupBy(name => name.charAt(0)))

  // 1
  // 2
  type Network = Map[String, Set[String]]

  def add(network: Network, person: String): Network =
    network + (person -> Set())

  def friend(network: Network, a: String, b: String): Network = {
    val friendsA: Set[String] = network(a)
    val friendsB: Set[String] = network(b)

    val aWithFriends: (String, Set[String]) = (a, friendsA + b)
    val bWithFriends: (String, Set[String]) = (b, friendsB + a)

    network + aWithFriends + bWithFriends
  }

  def unfriend(network: Network, a: String, b: String): Network = {
    val friendsA: Set[String] = network(a)
    val friendsB: Set[String] = network(b)

    val aFriendsWithoutB: (String, Set[String]) = (a, friendsA - b)
    val bFriendsWithoutB: (String, Set[String]) = (b, friendsB - a)

    network + aFriendsWithoutB + bFriendsWithoutB
  }

  def remove(network: Network, person: String): Network = {
    @tailrec
    def aux(friends: Set[String], networkAcc: Network): Network =
      if (friends.isEmpty) networkAcc
      else aux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = aux(network(person), network)
    unfriended - person
  }

  val empty: Network = Map.empty
  val network = add(add(empty, "bob"), "mary")
  println(network)
  println(friend(network, "bob", "mary"))
  println(unfriend(network, "bob", "mary"))
  println(remove(network, "bob"))

  //
  val people = add(add(add(empty, "carlos"), "alberto"), "micaela")
  val carlosAlberto = friend(people, "carlos", "alberto")
  val testNetwork = friend(carlosAlberto, "carlos", "micaela")

  println(testNetwork)

  def nFriends(network: Network, person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  println(nFriends(testNetwork, "carlos"))

  def mostFriends(network: Network): String =
    network.maxBy { case (_, friends) => friends.size }._1

  println(mostFriends(testNetwork))

  def nPeopleWithNoFriends(network: Network): Int =
    network.count { case (_, friends) => friends.isEmpty }

  def socialConnection(network: Network, a: String, b: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean =
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }

    bfs(b, Set(), network(a) + a)
  }

}
