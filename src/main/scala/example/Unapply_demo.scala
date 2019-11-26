package example

object Unapply_demo {

  class A[T](val t: T)

  object A {

    def apply[T](t: T): A[T] = new A(t)

    def unapply[T](arg: A[T]): Option[T] = arg match {
      case p =>
        println(s"ok")
        Some(p.t)
    }

  }

  object A2 {

//    def apply[T](t: T): A[T] = new A(t)

    def unapply[T](arg: A[T]): Option[T] = arg match {
      case p =>
        println(s"ok")
//        Some(p.t)
        None
    }

  }

  def main(args: Array[String]): Unit = {

    val a = A(44)
    val b = a match {
      case A2(v) =>
        println(s"v is $v")
      case _ => 0
    }

  }
}
