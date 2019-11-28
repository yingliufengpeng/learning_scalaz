package day08

object Pc_Composing_monadic_functions {

  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

    /**
     * scala> val f = Kleisli { (x: Int) => (x + 1).some }
     * f: scalaz.Kleisli[Option,Int,Int] = scalaz.KleisliFunctions$$anon$18@7da2734e
     *
     * scala> val g = Kleisli { (x: Int) => (x * 100).some }
     * g: scalaz.Kleisli[Option,Int,Int] = scalaz.KleisliFunctions$$anon$18@49e07991
     * We can then compose the functions using <=<, which runs rhs first like f compose g:
     *
     * scala> 4.some >>= (f <=< g)
     * res59: Option[Int] = Some(401)
     *
     * There’s also >=>, which runs lhs first like f andThen g:
     *
     * scala> 4.some >>= (f >=> g)
     * res60: Option[Int] = Some(500)
     */


    /**
     *
     * type ReaderT[F[+_], E, A] = Kleisli[F, E, A]
     * type Reader[E, A] = ReaderT[Id, E, A]
     * object Reader {
     * def apply[E, A](f: E => A): Reader[E, A] = Kleisli[Id, E, A](f)
     * }
     *
     * scala> val addStuff: Reader[Int, Int] = for {
     * a <- Reader { (_: Int) * 2 }
     * b <- Reader { (_: Int) + 10 }
     * } yield a + b
     * addStuff: scalaz.Reader[Int,Int] = scalaz.KleisliFunctions$$anon$18@343bd3ae
     *
     * scala> addStuff(3)
     * res76: scalaz.Id.Id[Int] = 19
     */




  }

}
