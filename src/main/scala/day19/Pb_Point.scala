package day19

import scalaz.Equal

object Pb_Point {


  def main(args: Array[String]): Unit = {

    /**
     * One very useful sort of set is a ‘singleton’ set, a set with exactly one element.
     * Fix one of these, say {me}, and call this set ’1‘.
     *
     * Definition: A point of a set X is an arrows 1 => X. … (If A is some familiar set,
     * an arrow from A to X is called an ’A-element’ of X; thus ’1-elements’ are points.)
     * Since a point is an arrow, we can compose it with another arrow, and get a point again
     */
    sealed trait Person {}
    case object John extends Person {}
    case object Mary extends Person {}
    case object Sam extends Person {}

    sealed trait Breakfast {}
    case object Eggs extends Breakfast {}
    case object Oatmeal extends Breakfast {}
    case object Toast extends Breakfast {}
    case object Coffee extends Breakfast {}

    val favoriteBreakfast: Person => Breakfast = {
      case John => Eggs
      case Mary => Coffee
      case Sam  => Coffee
    }

    val favoritePerson: Person => Person = {
      case John => Mary
      case Mary => John
      case Sam  => Mary
    }

    val favoritePersonsBreakfast = favoriteBreakfast compose favoritePerson

    val johnPoint: Unit => Person = { case () => John }

    val johnbreakfast =  favoriteBreakfast compose johnPoint

    import org.scalacheck.{Prop, Arbitrary, Gen}
    import scalaz._
    import scalaz.Scalaz._


    implicit val arbPerson: Arbitrary[Person] = Arbitrary {
      Gen.oneOf(John, Mary, Sam)
    }

    implicit val breakfastEqual: Equal[Breakfast] = Equal.equalA[Breakfast]

    def arrowEqualsProp[A, B](f: A => B, g: A => B)
                             (implicit ev1: Equal[B], ev2: Arbitrary[A]): Prop =
      Prop.forAll { a: A =>
        f(a) === g(a)
      }

    val r = arrowEqualsProp(favoriteBreakfast, favoriteBreakfast)

    r.check






  }
}
