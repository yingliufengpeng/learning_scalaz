package day16

object Pa_Memo {

  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

    // round up to 四舍五入
    lazy val memoizedFib: Int => Int = Memo.immutableHashMapMemo[Int, Int] {
      case 0 => 0
      case 1 => 1
      case n => memoizedFib(n - 2) + memoizedFib(n - 1)
    }

    val r = memoizedFib(30)
    println(s"r is $r")

    val r2 = memoizedFib(40)
    println(s"r2 is $r2")

    val r3 = memoizedFib(60)
    println(s"r3 is $r3")


  }
}
