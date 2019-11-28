package day07

object Pc_Either_Scalaz {


  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

    /**
     * scala> 1.right[String]
     * res12: scalaz.\/[String,Int] = \/-(1)
     *
     * scala> "error".left[Int]
     * res13: scalaz.\/[String,Int] = -\/(error)
     */

    /**
     * This is nice. Let’s try using it in for syntax:
     *
     * scala> for {
     * e1 <- "event 1 ok".right
     * e2 <- "event 2 failed!".left[String]
     * e3 <- "event 3 failed!".left[String]
     * } yield (e1 |+| e2 |+| e3)
     * res24: scalaz.\/[String,String] = -\/(event 2 failed!)
     * As you can see, the first failure rolls up as the final result. How do we get the value out of \/? First there’s isRight and isLeft method to check which side we are on:
     *
     * scala> "event 1 ok".right.isRight
     * res25: Boolean = true
     *
     * scala> "event 1 ok".right.isLeft
     * res26: Boolean = false
     * For right side, we can use getOrElse and its symbolic alias | as follows:
     *
     * scala> "event 1 ok".right | "something bad"
     * res27: String = event 1 ok
     * For left value, we can call swap method or it’s symbolic alias unary_~:
     *
     * scala> ~"event 2 failed!".left[String] | "something good"
     * res28: String = event 2 failed!
     * We can use map to modify the right side value:
     *
     * scala> "event 1 ok".right map {_ + "!"}
     * res31: scalaz.\/[Nothing,String] = \/-(event 1 ok!)
     * To chain on the left side, there’s orElse, which accepts => AA \/ BB where [AA >: A, BB >: B]. The symbolic alias for orElse is |||:
     *
     * scala> "event 1 failed!".left ||| "retry event 1 ok".right
     * res32: scalaz.\/[String,String] = \/-(retry event 1 ok)
     */
  }
}
