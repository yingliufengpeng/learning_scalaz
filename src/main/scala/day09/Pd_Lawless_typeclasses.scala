package day09

object Pd_Lawless_typeclasses {

  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

    List(1, 2, 3).index(3)

    /**
     * scala> List(1, 2, 3)(3)
     * java.lang.IndexOutOfBoundsException: 3
     * ...
     *
     * scala> List(1, 2, 3) index 3
     * res62: Option[Int] = None
     */
  }

}
