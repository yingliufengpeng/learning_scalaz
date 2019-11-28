package day08

object Pb_Making_a_safe_RPN_calculator {

  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

    def foldingFunction(list: List[Double], next: String): List[Double] = (list, next) match {
      case (x :: y :: ys, "*") => (y * x) :: ys
      case (x :: y :: ys, "+") => (y + x) :: ys
      case (x :: y :: ys, "-") => (y - x) :: ys
      case (xs, numString) => numString.toInt :: xs
    }

    def solveRPN(s: String): Double =
      (s.split(' ').toList.foldLeft(Nil: List[Double]) {foldingFunction}).head

    val r = solveRPN("10 4 3 + 2 * -")
    println(s"r is $r")

    def foldingFunction2(list: List[Double], next: String): Option[List[Double]] = (list, next) match {
      case (x :: y :: ys, "*") => ((y * x) :: ys).pure[Option]
      case (x :: y :: ys, "+") => ((y + x) :: ys).point[Option]
      case (x :: y :: ys, "-") => ((y - x) :: ys).point[Option]
      case (xs, numString) => numString.parseInt.toOption map {_ :: xs}
    }

    /**
     * scala> foldingFunction2(List(3, 2), "*")
     * res33: Option[List[Double]] = Some(List(6.0))
     *
     * scala> foldingFunction2(Nil, "*")
     * res34: Option[List[Double]] = None
     *
     * scala> foldingFunction2(Nil, "wawa")
     * res35: Option[List[Double]] = None
     */

    def solveRPN2(s: String): Option[Double] = for {
      List(x) <- s.split(' ').toList.foldLeftM(Nil: List[Double]) {foldingFunction2}
    } yield x

    val r2 = solveRPN("1 2 * 4 +")
    println(s"r2 is $r2")
  }

}
