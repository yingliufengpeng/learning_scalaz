package day12

object Pb_the_Essence_of_the_Iterator_Pattern {


  def main(args: Array[String]): Unit = {
    import scalaz._
    import scalaz.Scalaz._
    val r = Monoid[Int].applicative.ap2(1, 1)(0)
    println(s"r is $r")

    val r2 =  Monoid[List[Int]].applicative.ap2(List(1), List(1))(Nil)
    println(s"r2 is $r2")

    /**
     * scala> Applicative[List].product[Option]
     * res0: scalaz.Applicative[[α](List[α], Option[α])] = scalaz.Applicative$$anon$2@211b3c6a
     *
     * scala> Applicative[List].product[Option].point(1)
     * res1: (List[Int], Option[Int]) = (List(1),Some(1))
     */

    /**
     * scala> ((List(1), 1.some) |@| (List(1), 1.some)) {_ |+| _}
     * res2: (List[Int], Option[Int]) = (List(1, 1),Some(2))
     *
     * scala> ((List(1), 1.success[String]) |@| (List(1), "boom".failure[Int])) {_ |+| _}
     * res6: (List[Int], scalaz.Validation[String,Int]) = (List(1, 1),Failure(boom))
     */

    val r3 =  Applicative[List].compose[Option].point(1)
    println(s"r3 is $r3")

    /**
     * scala> Applicative[List].compose[Option]
     * res7: scalaz.Applicative[[α]List[Option[α]]] = scalaz.Applicative$$anon$1@461800f1
     *
     * scala> Applicative[List].compose[Option].point(1)
     * res8: List[Option[Int]] = List(Some(1))
     */

    /**
     * scala> List(1, 2, 3) traverse { x => (x > 0) option (x + 1) }
     * res14: Option[List[Int]] = Some(List(2, 3, 4))
     *
     * scala> List(1, 2, 0) traverse { x => (x > 0) option (x + 1) }
     * res15: Option[List[Int]] = None
     */

    /**
     * scala> Monoid[Int].applicative.traverse(List(1, 2, 3)) {_ + 1}
     * res73: Int = 9
     */

    def contents2[F[_]: Traverse, A](f: F[A]): List[A] =
      Monoid[List[A]].applicative.traverse(f) {List(_)}

    def contents[F[_]: Traverse, A](f: F[A]): List[A] =
      f.traverse[Lambda[x => List[A]], A] {List(_)}

    def collect[F[_]: Traverse, A, S, B](t: F[A])(f: A => B)(g: S => S): State[S, F[B]] =
      t.traverseS[S, B] { a => State { (s: S) => (g(s), f(a)) } }

    val loop = collect(List(1, 2, 3, 4)) {(_: Int) * 2} {(_: Int) + 1}
    println(s"loop is ${loop(0)}")

    def label[F[_]: Traverse, A](f: F[A]): F[Int] =
      (f.traverseS {_ => for {
        n <- get[Int]
        _ <- put(n + 1)
      } yield n}) eval 0

    val r4 = label(List(10, 2, 8))
    println(s"r4 is $r4")
  }
}
