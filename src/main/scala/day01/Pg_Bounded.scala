package day01

object Pg_Bounded {

  def main(args: Array[String]): Unit = {
    import scalaz._
    import scalaz.Scalaz._

    val r = implicitly[Enum[Char]].min
    println(s"r is $r")

    val r2 = implicitly[Enum[Char]].max
    println(s"r2 is $r2")

//    val r3 = implicitly[Enum[Double]].max
//    println(s"r3 is $r3")

    val r4 = implicitly[Enum[Int]].min
    println(s"r4 is $r4")


  }
}
