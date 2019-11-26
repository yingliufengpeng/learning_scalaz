package day00

object Pb_sum_function {

  def main(args: Array[String]): Unit = {
    def sum(xs: List[Int]): Int = xs.foldLeft(0) { _ + _ }

//    object IntMonoid {
//      def mappend(a: Int, b: Int): Int = a + b
//      def mzero: Int = 0
//    }

//    def sum2(xs: List[Int]): Int = xs.foldLeft(IntMonoid.mzero)(IntMonoid.mappend)


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

//    object IntMonoid extends Monoid[Int] {
//      def mappend(a: Int, b: Int): Int = a + b
//      def mzero: Int = 0
//    }

    def sum3(xs: List[Int], m: Monoid[Int]): Int = xs.foldLeft(m.mzero)(m.mappend)

    def sum4[A](xs: List[A], m: Monoid[A]): A = xs.foldLeft(m.mzero)(m.mappend)

    def sum5[A](xs: List[A])(implicit m: Monoid[A]): A = xs.foldLeft(m.mzero)(m.mappend)

//    implicit val intMonoid = IntMonoid

    val multiMonoid: Monoid[Int] = new Monoid[Int] {
      def mappend(a: Int, b: Int): Int = a * b
      def mzero: Int = 1
    }
    val r = sum5(List(1, 2, 3, 4))(multiMonoid)
    println(s"r is $r")
  }
}
