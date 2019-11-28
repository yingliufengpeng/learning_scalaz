package day05

object Pa_A_fist_full_of_Monads {

  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

    /**
     * scala> 3.some flatMap { x => (x + 1).some }
     * res2: Option[Int] = Some(4)
     *
     * scala> (none: Option[Int]) flatMap { x => (x + 1).some }
     * res3: Option[Int] = None
     */

    /**
     * scala> Monad[Option].point("WHAT")
     * res5: Option[String] = Some(WHAT)
     *
     * scala> 9.some flatMap { x => Monad[Option].point(x * 10) }
     * res6: Option[Int] = Some(90)
     *
     * scala> (none: Option[Int]) flatMap { x => Monad[Option].point(x * 10) }
     * res7: Option[Int] = None
     */
  }

}
