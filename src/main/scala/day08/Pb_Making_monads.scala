package day08

object Pb_Making_monads {

  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._


    case class Prob[A](list: List[(A, Double)])

    trait ProbInstances {
      def flatten[B](xs: Prob[Prob[B]]): Prob[B] = {
        def multall(innerxs: Prob[B], p: Double): Seq[(B, Double)] =
          innerxs.list map { case (x, r) => (x, p * r) }

        Prob(xs.list flatMap { case (innerxs, p) => multall(innerxs, p) })
      }

      implicit val probInstance: Functor[Prob] with Monad[Prob] = new Functor[Prob] with Monad[Prob] {
        def point[A](a: => A): Prob[A] = Prob((a, 1.0) :: Nil)

        def bind[A, B](fa: Prob[A])(f: A => Prob[B]): Prob[B] = flatten(map(fa)(f))

        override def map[A, B](fa: Prob[A])(f: A => B): Prob[B] =
          Prob(fa.list map { case (x, p) => (f(x), p) })
      }

      implicit def probShow[A]: Show[Prob[A]] = Show.showA
    }

    case object Prob extends ProbInstances

    sealed trait Coin
    case object Heads extends Coin
    case object Tails extends Coin
    implicit val coinEqual: Equal[Coin] = Equal.equalA

    def coin: Prob[Coin] = Prob(Heads -> 0.5 :: Tails -> 0.5 :: Nil)

    def loadedCoin: Prob[Coin] = Prob(Heads -> 0.1 :: Tails -> 0.9 :: Nil)

    def flipThree: Prob[Boolean] = for {
      a <- coin
      b <- coin
      c <- loadedCoin
    } yield {
      List(a, b, c) all {
        _ === Tails
      }
    }

    val r = flipThree
    println(s"r is $r")
  }

}
