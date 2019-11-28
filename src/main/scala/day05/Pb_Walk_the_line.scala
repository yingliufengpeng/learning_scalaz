package day05

object Pb_Walk_the_line {


  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

    type Birds = Int

    case class Pole(left: Birds, right: Birds) {
      def landLeft(n: Birds): Option[Pole] =
        if (math.abs((left + n) - right) < 4) copy(left = left + n).some
        else none

      def landRight(n: Birds): Option[Pole] =
        if (math.abs(left - (right + n)) < 4) copy(right = right + n).some
        else none

      def banana: Option[Pole] = none
    }

    val r = Monad[Option].point(Pole(0, 0)) >>= {
      _.landLeft(1)
    } >>= {
      _.banana
    } >>= {
      _.landRight(1)
    }
    println(s"r is $r")

    def routine: Option[Pole] =
      for {
        start <- Monad[Option].point(Pole(0, 0))
        first <- start.landLeft(2)
        _ <- (none: Option[Pole])
        second <- first.landRight(2)
        third <- second.landLeft(1)
      } yield third

    val r2 = routine
    println(s"r2 is $r2")

    def justH: Option[Char] =
      for {
        x :: _ <- "ee".toList.some
      } yield x

    val r3 = justH
    println(s"r3 is $r3")
  }
}
