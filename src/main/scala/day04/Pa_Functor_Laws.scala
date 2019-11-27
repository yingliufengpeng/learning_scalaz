package day04

object Pa_Functor_Laws {


  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._


    // step1 functor law
    /**
     *  law 1
     *  All functors are expected to exhibit certain kinds of functor-like properties
     *  and behaviors. … The first functor law states that if we map the id function
     *  over a functor, the functor that we get back should be the same as the original
     *  functor.
     */

//    scala> List(1, 2, 3) map {identity} assert_=== List(1, 2, 3)

    /**
     * law 2
     * The second law says that composing two functions and then mapping the resulting
     * function over a functor should be the same as first mapping one function over the
     * functor and then mapping the other one.
     */

    // (List(1, 2, 3) map {{(_: Int) * 3} map {(_: Int) + 1}}) assert_=== (List(1, 2, 3) map {(_: Int) * 3} map {(_: Int) + 1})

    import scalaz._, Scalaz._, scalacheck.ScalazProperties._, scalacheck.ScalazArbitrary._,scalacheck.ScalaCheckBinding._
    sealed trait COption[+A] {}
    case class CSome[A](counter: Int, a: A) extends COption[A]
    case object CNone extends COption[Nothing]

    implicit def coptionEqual[A]: Equal[COption[A]] = Equal.equalA
    implicit val coptionFunctor: Functor[COption] = new Functor[COption] {
      def map[A, B](fa: COption[A])(f: A => B): COption[B] = fa match {
        case CNone => CNone
        case CSome(c, a) => CSome(c + 1, f(a))
      }
    }

    val r = (CSome(0, "ho"): COption[String]) map {(_: String) + "ha"}
    println(s"r is $r")

    val r2 = (CSome(0, "ho"): COption[String]) map {identity}
    println(s"r2 is $r2")

    import org.scalacheck.{Gen, Arbitrary}

    implicit def COptionArbiterary[A](implicit a: Arbitrary[A]): Arbitrary[COption[A]] =
      a map { a => (CSome(0, a): COption[A]) }

//    functor.laws[COption].check(org.scalacheck.Test.Parameters.default)
    functor.laws[COption].check()


    // step2 Applicative law

    /**
     * trait ApplicativeLaw extends FunctorLaw {
     * def identityAp[A](fa: F[A])(implicit FA: Equal[F[A]]): Boolean =
     *       FA.equal(ap(fa)(point((a: A) => a)), fa)
     *
     * def composition[A, B, C](fbc: F[B => C], fab: F[A => B], fa: F[A])(implicit FC: Equal[F[C]]) =
     *       FC.equal(ap(ap(fa)(fab))(fbc), ap(fa)(ap(fab)(ap(fbc)(point((bc: B => C) => (ab: A => B) => bc compose ab)))))
     *
     * def homomorphism[A, B](ab: A => B, a: A)(implicit FB: Equal[F[B]]): Boolean =
     *       FB.equal(ap(point(a))(point(ab)), point(ab(a)))
     *
     * def interchange[A, B](f: F[A => B], a: A)(implicit FB: Equal[F[B]]): Boolean =
     *       FB.equal(ap(point(a))(f), ap(f)(point((f: A => B) => f(a))))
     * }
     */

    // step3 Semigroup Law

    /**
     * trait SemigroupLaw {
     * def associative(f1: F, f2: F, f3: F)(implicit F: Equal[F]): Boolean =
     *       F.equal(append(f1, append(f2, f3)), append(append(f1, f2), f3))
     * }
     */

    semigroup.laws[Int @@ Tags.Multiplication].check()

    // step 4 Monoid Law

    /**
     * trait MonoidLaw extends SemigroupLaw {
     * def leftIdentity(a: F)(implicit F: Equal[F]) = F.equal(a, append(zero, a))
     * def rightIdentity(a: F)(implicit F: Equal[F]) = F.equal(a, append(a, zero))
     * }
     */

    ((Monoid[Int @@ Tags.Multiplication].zero |+| Tags.Multiplication(2)).asInstanceOf[Int]) assert_=== 2
    (Tags.Multiplication(2) |+| Monoid[Int @@ Tags.Multiplication].zero ).asInstanceOf[Int] assert_=== 2


    monoid.laws[Int @@ Tags.Multiplication].check()
//    ((Monoid[Int @@ Tags.Multiplication].zero |+| Tags.Multiplication(2))) assert_=== 2

  }
}
