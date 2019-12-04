package day21

object Pa_Coproducts {


  def main(args: Array[String]): Unit = {
    import scalaz._
    import scalaz.Scalaz._
    def size(a: String \/ Int): Int = a match {
      case \/-(i) => i
      case -\/(s) => s.length
    }

    /**
     * scala> size(23.right[String])
     * res15: Int = 23
     *
     * scala> size("foo".left[Int])
     * res16: Int = 3
     */

    val m = Coproduct.left[Option](List(3))
    println(s"m is $m")

    val n = m.map(_ * 2)
    println(s"n is $n")


  }
}
