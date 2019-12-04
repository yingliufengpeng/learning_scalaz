package day19

import org.scalacheck.{Arbitrary, Gen, Prop}
import scalaz.Equal

object Pc_Isomorphisms {


  def main(args: Array[String]): Unit = {


    /**
     * Definitions: An arrow f: A => B is called an isomorphism,
     * or invertible(可逆性) arrow,Isomorphisms  if there is a map g: B => A, for which
     * g ∘ f = 1A and f ∘ g = 1B. An arrow g related to f by satisfying
     * these equations is called an inverse for f. Two objects A and B
     * are said to be isomorphic if there is at least one isomorphism(同构)
     * f: A => B.
     */

    import scalaz._
    import scalaz.Scalaz._
    sealed trait Family {}
    case object Mother extends Family {}
    case object Father extends Family {}
    case object Child extends Family {}

    sealed trait Relic {}
    case object Feather extends Relic {}
    case object Stone extends Relic {}
    case object Flower extends Relic {}

    import scalaz.Isomorphism.<=>
    val isoFamilyRelic: Family <=> Relic = new (Family <=> Relic) {
      val to: Family => Relic = {
        case Mother => Feather
        case Father => Stone
        case Child => Flower
      }
      val from: Relic => Family = {
        case Feather => Mother
        case Stone => Father
        case Flower => Child
      }
    }


    implicit val familyEqual: Equal[Family] = Equal.equalA[Family]
    implicit val relicEqual: Equal[Relic] = Equal.equalA[Relic]
    implicit val arbFamily: Arbitrary[Family] = Arbitrary {
      Gen.oneOf(Mother, Father, Child)
    }
    implicit val arbRelic: Arbitrary[Relic] = Arbitrary {
      Gen.oneOf(Feather, Stone, Flower)
    }

    def arrowEqualsProp[A, B](f: A => B, g: A => B)
                             (implicit ev1: Equal[B], ev2: Arbitrary[A]): Prop =
      Prop.forAll { a: A =>
        f(a) === g(a)
      }

    val r = arrowEqualsProp(isoFamilyRelic.from compose isoFamilyRelic.to, identity[Family])
    r.check

    val r2 = arrowEqualsProp(isoFamilyRelic.to compose isoFamilyRelic.from, identity[Relic])
    r2.check
  }
}
