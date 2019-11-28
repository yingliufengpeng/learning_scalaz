package day07

object Pa_Applicative_Builder {


  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

    /**
     * scala> (3.some |@| 5.some) {_ + _}
     * res18: Option[Int] = Some(8)
     *
     * scala> val f = ({(_: Int) * 2} |@| {(_: Int) + 10}) {_ + _}
     * f: Int => Int = <function1>
     */
  }
}
