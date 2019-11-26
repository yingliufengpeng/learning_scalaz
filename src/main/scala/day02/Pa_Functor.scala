package day02

object Pa_Functor {

  def main(args: Array[String]): Unit = {
    import scalaz._
    import scalaz.Scalaz._
    val r = (1, 2, 3, 4).map(_ + 1)
    println(s"r is $r")

    val r2 = ((x: Int) => x + 1) ∘ {_ * 7}
    println(s"r2 is ${r2(3)}")

    val r3 = ((x: Int) => x + 1) map {_ * 7}
    println(s"r2 is ${r3(3)}")

    val r4 = ((x: Int) => x + 1) andThen {_ * 7}
    println(s"r2 is ${r4(3)}")

    /**
     * How are functions functors? …
     *
     * What does the type fmap :: (a -> b) -> (r -> a) -> (r -> b) for
     * this instance tell us? Well, we see that it takes a function from a
     * to b and a function from r to a and returns a function from r to b.
     * Does this remind you of anything? Yes! Function composition!
     */

    val r5 = Functor[List].lift((_: Int) + 2)
    println(s"r5 is ${r5(List(44, 55))}")

    val res = List(1, 2, 3)

    val r6 = res >| "x"
    println(s"r6 is $r6")

    val r7 = res as "x"
    println(s"r6 is $r7")

    val r8 = res.fpair
    println(s"r8 is ${r8}")

    /**
     * scala> List(1, 2, 3).strengthL("x")
     * res50: List[(String, Int)] = List((x,1), (x,2), (x,3))
     *
     * scala> List(1, 2, 3).strengthR("x")
     * res51: List[(Int, String)] = List((1,x), (2,x), (3,x))
     *
     * scala> List(1, 2, 3).void
     * res52: List[Unit] = List((), (), ())
     *
     */
  }
}
