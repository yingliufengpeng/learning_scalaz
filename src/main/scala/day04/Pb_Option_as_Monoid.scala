package day04

object Pb_Option_as_Monoid {


  def main(args: Array[String]): Unit = {

    /**
     * implicit def optionMonoid[A: Semigroup]: Monoid[Option[A]] = new Monoid[Option[A]] {
     * def append(f1: Option[A], f2: => Option[A]) = (f1, f2) match {
     * case (Some(a1), Some(a2)) => Some(Semigroup[A].append(a1, a2))
     * case (Some(a1), None)     => f1
     * case (None, Some(a2))     => f2
     * case (None, None)         => None
     * }
     *
     * def zero: Option[A] = None
     * }
     */

    import scalaz._
    import scalaz.Scalaz._

    /**
     * scala> (none: Option[String]) |+| "andy".some
     * res23: Option[String] = Some(andy)
     *
     * scala> (Ordering.LT: Ordering).some |+| none
     * res25: Option[scalaz.Ordering] = Some(LT)
     */

    /**
     * scala> Tags.First('a'.some) |+| Tags.First('b'.some)
     * res26: scalaz.@@[Option[Char],scalaz.Tags.First] = Some(a)
     *
     * scala> Tags.First(none: Option[Char]) |+| Tags.First('b'.some)
     * res27: scalaz.@@[Option[Char],scalaz.Tags.First] = Some(b)
     *
     * scala> Tags.First('a'.some) |+| Tags.First(none: Option[Char])
     * res28: scalaz.@@[Option[Char],scalaz.Tags.First] = Some(a)
     */

    /**
     * scala> Tags.Last('a'.some) |+| Tags.Last('b'.some)
     * res29: scalaz.@@[Option[Char],scalaz.Tags.Last] = Some(b)
     *
     * scala> Tags.Last(none: Option[Char]) |+| Tags.Last('b'.some)
     * res30: scalaz.@@[Option[Char],scalaz.Tags.Last] = Some(b)
     *
     * scala> Tags.Last('a'.some) |+| Tags.Last(none: Option[Char])
     * res31: scalaz.@@[Option[Char],scalaz.Tags.Last] = Some(a)
     */
  }
}
