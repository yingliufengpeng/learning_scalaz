package day03

object Pc_About_those_Monoids {

  def main(args: Array[String]): Unit = {


    /**
     * A monoid is when you have an associative binary function and
     * a value which acts as an identity with respect to that function.
     */
    import scalaz._
    import scalaz.Scalaz._
    val r = List(1, 2, 3) mappend List(4, 5, 6)
    println(s"r is $r")

    val r2 = "one" |+| "two"
    println(s"r2 is $r2")

    val r3 = Tags.Multiplication(10) |+| Monoid[Int @@ Tags.Multiplication].zero
    println(s"r3 is $r3")

    val r4 = 3 |+| Monoid[Int].zero
    println(s"r4 is $r4")

    val r5 = Tags.Disjunction(true) |+| Tags.Disjunction(false)

    /**
     * Another type which can act like a monoid in two distinct but equally
     * valid ways is Bool. The first way is to have the or function || act
     * as the binary function along with False as the identity value. …
     * The other way for Bool to be an instance of Monoid is to kind of do
     * the opposite: have && be the binary function and then make True the
     * identity value.
     */

    /**
     * scala> Tags.Disjunction(true) |+| Tags.Disjunction(false)
     * res28: scalaz.@@[Boolean,scalaz.Tags.Disjunction] = true
     *
     * scala> Monoid[Boolean @@ Tags.Disjunction].zero |+| Tags.Disjunction(true)
     * res29: scalaz.@@[Boolean,scalaz.Tags.Disjunction] = true
     *
     * scala> Monoid[Boolean @@ Tags.Disjunction].zero |+| Monoid[Boolean @@ Tags.Disjunction].zero
     * res30: scalaz.@@[Boolean,scalaz.Tags.Disjunction] = false
     *
     * scala> Monoid[Boolean @@ Tags.Conjunction].zero |+| Tags.Conjunction(true)
     * res31: scalaz.@@[Boolean,scalaz.Tags.Conjunction] = true
     *
     * scala> Monoid[Boolean @@ Tags.Conjunction].zero |+| Tags.Conjunction(false)
     * res32: scalaz.@@[Boolean,scalaz.Tags.Conjunction] = false
     */


//    Ordering.LT |+| Ordering.GT

    val r6 = (Ordering.LT: Ordering) |+| (Ordering.GT: Ordering)
    println(s"r6 is $r6")

    /**
     * scala> (Ordering.LT: Ordering) |+| (Ordering.GT: Ordering)
     * res42: scalaz.Ordering = LT
     *
     * scala> (Ordering.GT: Ordering) |+| (Ordering.LT: Ordering)
     * res43: scalaz.Ordering = GT
     *
     * scala> Monoid[Ordering].zero |+| (Ordering.LT: Ordering)
     * res44: scalaz.Ordering = LT
     *
     * scala> Monoid[Ordering].zero |+| (Ordering.GT: Ordering)
     * res45: scalaz.Ordering = GT
     */


    def lengthCompare(lhs: String, rhs: String): Ordering =
      (lhs.length ?|? rhs.length) |+| (lhs ?|? rhs)

    /**
     * scala> def lengthCompare(lhs: String, rhs: String): Ordering =
     * (lhs.length ?|? rhs.length) |+| (lhs ?|? rhs)
     * lengthCompare: (lhs: String, rhs: String)scalaz.Ordering
     *
     * scala> lengthCompare("zen", "ants")
     * res46: scalaz.Ordering = LT
     *
     * scala> lengthCompare("zen", "ant")
     * res47: scalaz.Ordering = GT
     */

  }
}
