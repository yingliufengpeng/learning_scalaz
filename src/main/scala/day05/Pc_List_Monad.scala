package day05

object Pc_List_Monad {


  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

    /**
     * scala> ^(List(1, 2, 3), List(10, 100, 100)) {_ * _}
     * res29: List[Int] = List(10, 100, 100, 20, 200, 200, 30, 300, 300)
     **/

    /**
     *
     * scala> List(3, 4, 5) >>= {x => List(x, -x)}
     * res30: List[Int] = List(3, -3, 4, -4, 5, -5)
     */

    /**
     * scala> for {
     * n <- List(1, 2)
     * ch <- List('a', 'b')
     * } yield (n, ch)
     * res33: List[(Int, Char)] = List((1,a), (1,b), (2,a), (2,b))
     */
  }
}
