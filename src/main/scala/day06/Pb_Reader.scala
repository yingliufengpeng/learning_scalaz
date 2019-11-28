package day06

object Pb_Reader {


  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

    /**
     * scala> val f = (_: Int) * 5
     * f: Int => Int = <function1>
     *
     * scala> val g = (_: Int) + 3
     * g: Int => Int = <function1>
     *
     * scala> (g map f)(8)
     * res22: Int = 55
     */

    /**
     * scala> val f = ({(_: Int) * 2} |@| {(_: Int) + 10}) {_ + _}
     * warning: there were 1 deprecation warnings; re-run with -deprecation for details
     * f: Int => Int = <function1>
     *
     * scala> f(3)
     * res35: Int = 19
     */

    /**
     * scala> val addStuff: Int => Int = for {
     * a <- (_: Int) * 2
     * b <- (_: Int) + 10
     * } yield a + b
     * addStuff: Int => Int = <function1>
     *
     * scala> addStuff(3)
     * res39: Int = 19
     *
     * Both (*2) and (+10) get applied to the number 3 in this case. return (a+b) does as well,
     * but it ignores it and always presents a+b as the result. For this reason, the function monad
     * is also called the reader monad. All the functions read from a common source.
     */


  }
}
