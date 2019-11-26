package example

object Disjunction_demo {

  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.\/
    val l: String \/ Int = \/.left("3")
    val r: String \/ Int = \/.right(4)
    println(s"l is $l r is $r")

    l match {
      case -\/(left) => println(s"left is $left")
      case \/-(right) => println(s"right is $right")
    }

    val r2 = r.flatMap(right => \/-(right))
    println(s"r2 is $r2")

    val l2 = l.flatMap(left => \/.left(left))
    println(s"l2 is $l2")

  }

}
