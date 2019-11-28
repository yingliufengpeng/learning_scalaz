package day08

object Pa_Some_useful_monadic_functions {

  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

    /**
     * It turns out that any nested monadic value can be flattened and
     * that this is actually a property unique to monads. For this, the
     * join function exists.
     */

    /**
     * scala> (Some(9.some): Option[Option[Int]]).join
     * res9: Option[Int] = Some(9)
     *
     * scala> (Some(none): Option[Option[Int]]).join
     * res10: Option[Int] = None
     *
     * scala> List(List(1, 2, 3), List(4, 5, 6)).join
     * res12: List[Int] = List(1, 2, 3, 4, 5, 6)
     *
     * scala> 9.right[String].right[String].join
     * res15: scalaz.Unapply[scalaz.Bind,scalaz.\/[String,scalaz.\/[String,Int]]]{type M[X] = scalaz.\/[String,X]; type A = scalaz.\/[String,Int]}#M[Int] = \/-(9)
     *
     * scala> "boom".left[Int].right[String].join
     * res16: scalaz.Unapply[scalaz.Bind,scalaz.\/[String,scalaz.\/[String,Int]]]{type M[X] = scalaz.\/[String,X]; type A = scalaz.\/[String,Int]}#M[Int] = -\/(boom)
     *
     */

    // 很显然都是以右侧为主进行压平的操作
    9.right[String].right[String].join
    "boom".left[Int].right[String].join

    // filterM 中的 final def filterM[M[_] : Applicative](p: A => M[Boolean]): M[List[A]] = l.filterM(self)(p)
    List(1, 2, 3) filterM { _ => List(true, false) }

    // 这种的组合的性质感觉很有趣
    val m = Vector(1, 2, 3) filterM { _ => Vector(true, false) }
    println(s"m is $m")

    def binSmalls(acc: Int, x: Int): Option[Int] = {
      if (x > 9) (none: Option[Int])
      else (acc + x).some
    }

    val r = List(2, 8, 3, 1).foldLeftM(0) {binSmalls}
    println(s"r is $r")

    val r2 = List(2, 11, 3, 1).foldLeftM(0) {binSmalls}
    println(s"r2 is $r2")


  }

}
