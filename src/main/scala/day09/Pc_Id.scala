package day09

object Pc_Id {

  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

    /**
     * type Id[+X] = X
     * We need to look at monad transformer later, but one thing that’s interesting is that all data types can be Id of the type.
     *
     * scala> (0: Id[Int])
     * res39: scalaz.Scalaz.Id[Int] = 0
     */

    /**
     *
     * scala> 1 + 2 + 3 |> {_.point[List]}
     * res45: List[Int] = List(6)
     *
     * scala> 1 + 2 + 3 |> {_ * 6}
     * res46: Int = 36
     * visit is also kind of interesting:
     *
     * scala> 1 visit { case x@(2|3) => List(x * 2) }
     * res55: List[Int] = List(1)
     *
     * scala> 2 visit { case x@(2|3) => List(x * 2) }
     * res56: List[Int] = List(4)
     */
  }

}
