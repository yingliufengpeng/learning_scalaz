package day04

object Pc_Foldable {

  def main(args: Array[String]): Unit = {
    import scalaz._
    import scalaz.Scalaz._

    /**
     * trait Foldable[F[_]] { self =>
     * /** Map each element of the structure to a [[scalaz.Monoid]], and combine the results. */
     * def foldMap[A,B](fa: F[A])(f: A => B)(implicit F: Monoid[B]): B
     *
     * /**Right-associative fold of a structure. */
     * def foldRight[A, B](fa: F[A], z: => B)(f: (A, => B) => B): B
     *
     * ...
     * }
     */

    /**
     * scala> List(1, 2, 3) foldMap {identity}
     * res53: Int = 6
     *
     * scala> List(true, false, true, true) foldMap {Tags.Disjunction.apply}
     * res56: scalaz.@@[Boolean,scalaz.Tags.Disjunction] = true
     */

  }
}
