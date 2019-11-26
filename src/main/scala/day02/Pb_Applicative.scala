package day02

object Pb_Applicative {


  def main(args: Array[String]): Unit = {

    /**
     * scala> 1.point[List]
     * res14: List[Int] = List(1)
     *
     * scala> 1.point[Option]
     * res15: Option[Int] = Some(1)
     *
     * scala> 1.point[Option] map {_ + 2}
     * res16: Option[Int] = Some(3)
     *
     * scala> 1.point[List] map {_ + 2}
     * res17: List[Int] = List(3)
     *
     * scala> 3.pure[Either[String, ?]]
     * res11: scala.util.Either[String,Int] = Right(3)
     */
    import scalaz._
    import scalaz.Scalaz._
    val r = 9.some <*> {(_: Int) + 3}.some
    println(s"r is $r")

    /**
     * scala> 1.some <* 2.some
     * res35: Option[Int] = Some(1)
     *
     * scala> none <* 2.some
     * res36: Option[Nothing] = None
     *
     * scala> 1.some *> 2.some
     * res38: Option[Int] = Some(2)
     *
     * scala> none *> 2.some
     * res39: Option[Int] = None
     */

    /**
     * scala> 9.some <*> {(_: Int) + 3}.some
     * res57: Option[Int] = Some(12)
     */

    // 这个例子好
    val r2 = 3.some <*> (9.some <*> ((_: Int) + (_: Int)).curried.some)
    println(s"r2 is $r2")

    val r3 = ^(3.some, 5.some)(_ + _)
    println(s"r3 is $r3")

    // 其实这样的结合最终额结果是一个笛卡尔的乘积
    val r4 = (List("ha", "heh", "hmm") |@| List("?", "!", ".")) {_ + _}
    println(s"r4 is $r4")
    /**
     * scala> List(1, 2, 3) <*> List((_: Int) * 0, (_: Int) + 100, (x: Int) => x * x)
     * res61: List[Int] = List(0, 0, 0, 101, 102, 103, 1, 4, 9)
     *
     * scala> List(3, 4) <*> { List(1, 2) <*> List({(_: Int) + (_: Int)}.curried, {(_: Int) * (_: Int)}.curried) }
     * res62: List[Int] = List(4, 5, 5, 6, 3, 4, 6, 8)
     *
     * scala> (List("ha", "heh", "hmm") |@| List("?", "!", ".")) {_ + _}
     * res63: List[String] = List(ha?, ha!, ha., heh?, heh!, heh., hmm?, hmm!, hmm.)
     *
     */


    // zip操作 也就是说scalaz中切片这样的数据不容易实现

    val r6 = Apply[Option].lift2((_: Int) :: (_: List[Int]))
    println(s"r6 is $r6")

    /**
     * scala> Apply[Option].lift2((_: Int) :: (_: List[Int]))
     * res66: (Option[Int], Option[List[Int]]) => Option[List[Int]] = <function2>
     *
     * scala> res66(3.some, List(4).some)
     * res67: Option[List[Int]] = Some(List(3, 4))
     */

    // 可以把这里的F看成是Option
    def sequenceA[F[_]: Applicative, A](list: List[F[A]]): F[List[A]] = list match {
      case Nil => Nil.asInstanceOf[List[A]].pure[F]
      case x :: xs => ^(x, sequenceA(xs))(_ :: _)
    }

    val r7 = sequenceA(List(1.some, 2.some))
    println(s"r7 is $r7")


//    type Function1Int[A] = ({type l[A]=Function1[Int, A]})#l[A]
    type Function1Int[A]  = Int => A
//    type Function1Int[A] = Int => A

    val r8 = sequenceA(List(
      (_: Int) + 3,
      (_: Int) + 2,
      (_: Int) + 1))

    val r9 = sequenceA(List(_ + 3, _ + 4, _ + 5): List[Int => Int])
    val r10 = sequenceA(List(_ + 3, _ + 4, _ + 5): List[Function1Int[Int]])

    val r11 = r9(1)
    val r12 = r10(1)
    println(s"r11 is $r11, r12 is $r12")

    val f = (_: Int) + 1
    val g = (_: Int) + 2

    // Int => List[Int] = a => f(a) :: m(a)  应该是这样的计算结构 m: Int => List[Int]
    val f2 = ^(f, (_: Int) => List.empty[Int])((r, listr) => {
      //
      r :: listr
    })

    val f3: Int => List[Int] = ^(g, f2)(_ :: _)

    println(s"f3(3) is ${f3(3)}")

  }
}
