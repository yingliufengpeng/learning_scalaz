package day05

object Pf_Monad_law {


  def main(args: Array[String]): Unit = {

    // step 1 Left identity
    /**
     * The first monad law states that if we take a value, put it in a default context with
     * return and then feed it to a function by using >>=, it’s the same as just taking the
     * value and applying the function to it.
     */

    // (Monad[F].point(x) flatMap {f}) assert_=== f(x)

//    scala> (Monad[Option].point(3) >>= { x => (x + 100000).some }) assert_=== 3 |> { x => (x + 100000).some }
//    Right identity

    // step 2 Right identity

    /**
     * The second law states that if we have a monadic value and we use >>= to
     * feed it to return, the result is our original monadic value.
     */

    // (m forMap {Monad[F].point(_)}) assert_=== m

//    scala> ("move on up".some flatMap {Monad[Option].point(_)}) assert_=== "move on up".some

    // step 3
//    Associativity
//    The final monad law says that when we have a chain of monadic function applications with >>=, it shouldn’t matter how they’re nested.

    import scalaz._
    import scalaz.Scalaz._
    case class Pole(row: Int, col: Int) {
      def landLeft(row: Int): Option[Pole] = Some(copy(row = row + this.row))
      def landRight(col: Int): Option[Pole] = Some(copy(col = col + this.col))
    }

    val r = Monad[Option].pure(Pole(0, 0)) >>= {_.landLeft(2)}

    val r2 = Monad[Option].point(Pole(0, 0)) >>= { x =>
      x.landRight(2) >>= { y =>
        y.landLeft(2) >>= { z =>
          z.landRight(2)
        }}}


    /**
     * // (m flatMap f) flatMap g assert_=== m flatMap { x => f(x) flatMap {g} }
     *
     *
     * scala> Monad[Option].point(Pole(0, 0)) >>= {_.landRight(2)} >>= {_.landLeft(2)} >>= {_.landRight(2)}
     * res76: Option[Pole] = Some(Pole(2,4))
     *
     * scala> Monad[Option].point(Pole(0, 0)) >>= { x =>
     *        x.landRight(2) >>= { y =>
     *        y.landLeft(2) >>= { z =>
     *        z.landRight(2)
     * }}}
     * res77: Option[Pole] = Some(Pole(2,4))
     */
  }
}
