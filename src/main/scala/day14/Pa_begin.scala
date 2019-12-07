package day14

object Pa_begin {


  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

    /**
     *
     * scala> Vector(1, 2) >>= { x => Vector(x + 1)}
     * res0: scala.collection.immutable.Vector[Int] = Vector(2, 3)
     *
     * scala> Vector(1, 2) filterM { x => Vector(true, false) }
     * res1: scala.collection.immutable.Vector[Vector[Int]] = Vector(Vector(1, 2), Vector(1), Vector(2), Vector())
     */

    object Monoidal {
      def apply[A: Monoid](f: A => A): Kleisli[Lambda[`+α` => A], A, A] =
        Kleisli[Lambda[`+α` => A], A, A](f)
    }

    val r =  Monoidal { x: Int => x + 1 }
    println(s"r is $r")

    List(1, 2, 3) traverseU {_ + 1}

    /**
     *
     * scala> val f = { (x: Int) => x + 1 }
     * f: Int => Int = <function1>
     *
     * scala> val g = { (x: Int) => List(x, 5) }
     * g: Int => List[Int] = <function1>
     *
     * scala> val h = f &&& g
     * h: Int => (Int, List[Int]) = <function1>
     *
     * scala> List(1, 2, 3) traverseU f
     * res0: Int = 9
     *
     * scala> List(1, 2, 3) traverseU g
     * res1: List[List[Int]] = List(List(1, 2, 3), List(1, 2, 5), List(1, 5, 3), List(1, 5, 5), List(5, 2, 3), List(5, 2, 5), List(5, 5, 3), List(5, 5, 5))
     *
     * scala> List(1, 2, 3) traverseU h
     * res2: (Int, List[List[Int]]) = (9,List(List(1, 5), List(2, 5), List(3, 5)))
     */



  }
}
