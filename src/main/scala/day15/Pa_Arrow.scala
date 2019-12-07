package day15

object Pa_Arrow {


  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

    /**
     * scala> val f = (_:Int) + 1
     * f: Int => Int = <function1>
     *
     * scala> val g = (_:Int) * 100
     * g: Int => Int = <function1>
     *
     * scala> (f >>> g)(2)
     * res0: Int = 300
     *
     * scala> (f <<< g)(2)
     * res1: Int = 201
     *
     * scala> (f *** g)(1, 2)
     * res3: (Int, Int) = (2,200)
     *
     * scala> (f &&& g)(2)
     * res4: (Int, Int) = (3,200)
     */


  }
}
