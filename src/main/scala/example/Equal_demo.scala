package example

object Equal_demo {

  def main(args: Array[String]): Unit = {

//    import scalaz._
    import scalaz.Scalaz._
    val r = List(1, 2, 3) === List(1, 2, 3)
    println(s"r is $r")
  }

}
