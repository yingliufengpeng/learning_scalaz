package day03

object Pa_Kinds_and_some_type_foo {

  def main(args: Array[String]): Unit = {

    trait Test {
      type F[_]
    }

    val m = new Test {
//      type Id[A] = A
      override type F[A] = List[A]
    }

    val n: m.F[Int] = List(444)
    println(s"n is $n")

    /**
     * A first-order value, or a value constructor like (_: Int) + 3,
     * is normally called a function. Similarly, a first-order-kinded
     * type is a type that accepts other types to create a proper type.
     * This is normally called a type constructor. Option, Either, and
     * Equal are all first-order-kinded. To denote that these accept
     * other types, we use curried notation like * -> * and * -> * -> *.
     * Note, Option[Int] is *; Option is * -> *. Using Scala’s type variable
     * notation they could be written as F[+A] and F[+A1,+A2].
     *
     * A higher-order value like (f: Int => Int, list: List[Int]) => list map
     * {f}, a function that accepts other functions is normally called higher-order
     * function. Similarly, a higher-kinded type is a type constructor that accepts
     * other type constructors. It probably should be called a higher-kinded type
     * constructor but the name is not used. These are denoted as (* -> *) -> *.
     * Using Scala’s type variable notation this could be written as X[F[A]].
     */
  }

}
