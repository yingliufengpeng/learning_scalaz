package example

object case_class_demo {

  class MutableFair[A, B](val a: A, val b: B)

  object MutableFair {
    def apply[A, B](a: A, b: B) = new MutableFair(a, b)

    def unapply[A, B](arg: MutableFair[A, B]): Option[(A, B)] = Some(arg.a, arg.b)
  }

  def main(args: Array[String]): Unit = {


//    val r = MutableFair(4, 4)

    val r = null.asInstanceOf[MutableFair[Int, Int]]

    r match {
      case MutableFair(a, b) => println(s"a is $a, b is $b")
    }

  }
}
