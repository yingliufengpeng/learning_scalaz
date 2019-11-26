package day00

object Pa_What_is_polymorphism {

  def main(args: Array[String]): Unit = {
    // Parametric polymorphism
    def head[A](xs: List[A]): A = xs.head

    def plus[A](a1: A, a2: A): A = ???

    // Subtype polymorphism
    trait Plus[A] {
      def plus(a2: A): A
    }

    def plus2[A <: Plus[A]](a1: A, a2: A): A = ???

    // Ad-hoc polymorphism
    trait Plus2[A] {
      def plus(a1: A, a2: A): A
    }

    def plus3[A](a1: A, a2: A)(implicit plus2: Plus2[A]): A = plus2.plus(a1, a2)

    /**
     * This is truely ad-hoc in the sense that
     *
     * we can provide separate function definitions for different types of A
     * we can provide function definitions to types (like Int) without access to its source code
     * the function definitions can be enabled or disabled in different scopes
     */

  }

}
