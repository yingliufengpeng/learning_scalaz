package day01

object Pd_Show {


  def main(args: Array[String]): Unit = {
    import scalaz.Scalaz._
    val r = 3.show
    println(s"r is $r")

    val r2 = 3.shows
    println(s"r2 is $r2")

    "hello".println



  }
}
