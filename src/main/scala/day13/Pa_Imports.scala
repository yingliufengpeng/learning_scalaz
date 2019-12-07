package day13

object Pa_Imports {

  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._
    val r = for {
      xs <- get[List[Int]]
      _ <- put(xs.tail)
    } yield xs.head
    println(s"r is ${r.run(List(4))}")

    val r2 = intersperse(List(1, 2, 3, 4), 7)
    println(s"r2 is $r2")

    val r3 = 1.node(2.leaf)
    println(s"r3 is $r3")

    /**
     * scala> 1.set("log1")
     * res8: scalaz.Writer[String,Int] = scalaz.WriterTFunctions$$anon$26@2375d245
     *
     * scala> "log2".tell
     * res9: scalaz.Writer[String,Unit] = scalaz.WriterTFunctions$$anon$26@699289fb
     *
     * scala> 1.success[String]
     * res11: scalaz.Validation[String,Int] = Success(1)
     *
     * scala> "boom".failureNel[Int]
     * res12: scalaz.ValidationNEL[String,Int] = Failure(NonEmptyList(boom))
     */

    val r4 = 1.set("log1")
    println(s"r4 is $r4")

    /**
     * scala> false /\ true
     * res14: Boolean = false
     *
     * scala> false \/ true
     * res15: Boolean = true
     *
     * scala> true option "foo"
     * res16: Option[String] = Some(foo)
     *
     * scala> (1 > 10)? "foo" | "bar"
     * res17: String = bar
     *
     * scala> (1 > 10)?? {List("foo")}
     * res18: List[String] = List()
     */

    val r5 = 1.some | 2
    println(s"r5 is $r5")

    val r6 = 1.right[Int] | 2
    println(s"r6 is $r6")

    val r7 = (1 > 10)? "foo" | "bar" // 三元表达式
    println(s"r7 is $r7")
  }

}
