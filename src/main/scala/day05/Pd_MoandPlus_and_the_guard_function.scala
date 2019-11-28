package day05

object Pd_MoandPlus_and_the_guard_function {


  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._
    val r = for {
      x <- 1 |-> 50 if x.shows contains '7'
    } yield x

    println(s"r is $r")

    /**
     * scala> List(1, 2, 3) <+> List(4, 5, 6)
     * res43: List[Int] = List(1, 2, 3, 4, 5, 6)
     */

    /**
     * scala> (1 |-> 50) filter { x => x.shows contains '7' }
     * res46: List[Int] = List(7, 17, 27, 37, 47)
     */
  }
}
