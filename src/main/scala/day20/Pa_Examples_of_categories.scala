package day20

object Pa_Examples_of_categories {

  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

    /**
     * scala> 10 |+| Monoid[Int].zero
     * res26: Int = 10
     */

    /**
     * scala>  Tags.Multiplication(10) |+| Monoid[Int @@ Tags.Multiplication].zero
     * res27: scalaz.@@[Int,scalaz.Tags.Multiplication] = 10
     */
  }

}
