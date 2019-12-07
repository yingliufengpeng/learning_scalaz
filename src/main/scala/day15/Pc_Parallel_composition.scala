package day15

object Pc_Parallel_composition {

  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

    /**
     * scala> val text = "the cat in the hat\n sat on the mat\n".toList
     * text: List[Char] =
     * List(t, h, e,  , c, a, t,  , i, n,  , t, h, e,  , h, a, t,
     * ,  , s, a, t,  , o, n,  , t, h, e,  , m, a, t,
     * )
     *
     * scala> def count[A] = (a: A) => 1
     * count: [A]=> A => Int
     *
     * scala> val charCount = count[Char]
     * charCount: Char => Int = <function1>
     *
     * scala> text traverseU charCount
     * res10: Int = 35
     *
     * scala> import scalaz.std.boolean.test
     * import scalaz.std.boolean.test
     *
     * scala> val lineCount = (c: Char) => test(c === '\n')
     * lineCount: Char => Int = <function1>
     *
     * scala> text traverseU lineCount
     * res11: Int = 2
     *
     * scala> val wordCount = (c: Char) => for {
     * x <- get[Boolean]
     * val y = c =/= ' '
     * _ <- put(y)
     * } yield test(y /\ !x)
     * wordCount: Char => scalaz.StateT[scalaz.Id.Id,Int,Int] = <function1>
     *
     * scala> (text traverseU wordCount) eval false count(_ > 0)
     * res25: Int = 9
     *
     * scala> text traverseU { (c: Char) => XProduct(charCount(c), lineCount(c)) }
     * res26: scalaz.XProduct[Int,Int] = XProduct(35, 2)
     */

  }

}
