package day18

object Pa_Func {


  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

    val f = (x: Int) => x + 1
    val g = (x: Int) => List(x, 5)
    val r = (f &&& g)(2)
    println(s"r is $r")
  }
}
