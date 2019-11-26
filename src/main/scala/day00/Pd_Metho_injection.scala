package day00

import scala.language.implicitConversions

object Pd_Metho_injection {

  def main(args: Array[String]): Unit = {

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

    trait MonoidOp[A] {
      val F: Monoid[A]
      val value: A
      def |+|(a2: A): A = F.mappend(value, a2)
    }

    object MonoidOp {
      implicit def toMonoidOp[A: Monoid](a: A): MonoidOp[A] = new MonoidOp[A] {
        val F: Monoid[A] = implicitly[Monoid[A]]
        val value: A = a
      }
    }

//    import Monoid._
    import MonoidOp._
    val r = 3 |+| 4
    println(s"r is $r")

    val r2 = "a" |+| "b" |+| "a" |+| "b"
    println(s"r2 is $r2")
  }
}
