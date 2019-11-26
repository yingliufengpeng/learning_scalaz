package day00

object Pc_FoldLeft {

  def main(args: Array[String]): Unit = {

    object FoldLeftList {
      def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B): B = xs.foldLeft(b)(f)
    }

    trait Monoid[A] {
      def mappend(a1: A, a2: A): A
      def mzero: A
    }

    object Monoid {
      implicit val IntMonoid: Monoid[Int] = new Monoid[Int] {
        def mappend(a: Int, b: Int): Int = a + b
        def mzero: Int = 0
      }
      implicit val StringMonoid: Monoid[String] = new Monoid[String] {
        def mappend(a: String, b: String): String = a + b
        def mzero: String = ""
      }
    }

    trait FoldLeft[F[_]] {
      def foldLeft[A, B](xs: F[A], b: B, f: (B, A) => B): B
    }
    object FoldLeft {
      implicit val FoldLeftList: FoldLeft[List] = new FoldLeft[List] {
        def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B): B = xs.foldLeft(b)(f)
      }
    }

//    def sum[A](xs: List[A])(implicit m: Monoid[A]): A = xs.foldLeft(m.mzero)(m.mappend)

    def sum[M[_]: FoldLeft, A: Monoid](xs: M[A]): A = {
      val m = implicitly[Monoid[A]]
      val fl = implicitly[FoldLeft[M]]
      fl.foldLeft(xs, m.mzero, m.mappend)
    }

    val r = sum(List(1, 2, 3, 4))
    println(s"r is $r")

    val r2 = sum(List("addd", "b", "c"))
    println(s"r2 is $r2")
  }
}
