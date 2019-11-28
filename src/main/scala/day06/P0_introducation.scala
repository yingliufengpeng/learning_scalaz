package day06

object P0_introducation {

  def main(args: Array[String]): Unit = {


    import scalaz._
    import scalaz.Scalaz._

    def foo: Option[String] = for {
      x <- 3.some
      y <- "!".some
    } yield x.shows + y

    case class KnightPos(c: Int, r: Int) {
      def move: List[KnightPos] =
        for {
          KnightPos(c2, r2) <- List(KnightPos(c + 2, r - 1), KnightPos(c + 2, r + 1),
            KnightPos(c - 2, r - 1), KnightPos(c - 2, r + 1),
            KnightPos(c + 1, r - 2), KnightPos(c + 1, r + 2),
            KnightPos(c - 1, r - 2), KnightPos(c - 1, r + 2)) if (
            ((1 |-> 8) contains c2) && ((1 |-> 8) contains r2))
        } yield KnightPos(c2, r2)

      def in3: List[KnightPos] =
        for {
          first <- move
          second <- first.move
          third <- second.move
        } yield third

      def canReachIn3(end: KnightPos): Boolean = in3 contains end
    }


  }
}
