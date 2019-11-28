package day05

object Pe_A_knight_quest {


  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

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

    val r = KnightPos(6, 2) canReachIn3 KnightPos(6, 1)
    println(s"r is $r")

    val r2 = KnightPos(6, 2) canReachIn3 KnightPos(7, 3)
    println(s"r2 is $r2")


  }
}
