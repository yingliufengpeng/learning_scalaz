package day07

object Pb_Tasteful_stateful_computations {

  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._


    type Stack = List[Int]
    def pop(stack: Stack): (Int, Stack) = stack match {
      case x :: xs => (x, xs)
      case _ => (0, Nil)
    }

    def push(a: Int, stack: Stack): (Unit, Stack) = ((), a :: stack)

    def stackManip(stack: Stack): (Int, Stack) = {
      val (_, newStack1) = push(3, stack)
      val (a, newStack2) = pop(newStack1)
      pop(newStack2)
    }

    val r = stackManip(List(5, 8, 2, 1))
    println(s"r is $r")

    /**
     * type State[S, +A] = StateT[Id, S, A]
     *
     * // important to define here, rather than at the top-level, to avoid Scala 2.9.2 bug
     * object State extends StateFunctions {
     * def apply[S, A](f: S => (S, A)): State[S, A] = new StateT[Id, S, A] {
     * def apply(s: S) = f(s)
     * }
     * }
     */


    /**
     * scala> State[List[Int], Int] { case x :: xs => (xs, x) }
     * res1: scalaz.State[List[Int],Int] = scalaz.package$State$$anon$1@19f58949
     */

    val pop2 = State[Stack, Int] {
      case x :: xs => (xs, x)
      case Nil => (Nil, 0)
    }

    def push2(a: Int): State[Stack, Unit] = State[Stack, Unit] { s =>
      (a :: s, ())
    }

    // 再一次体会到了function programming的强大之处!!!
    def stackManip2: State[Stack, Int] = for {
      _ <- push2(3)
      _ <- pop2
      b <- pop2
    } yield(b)

    val r2 = stackManip2(List(5, 8, 2, 1))
    println(s"r2 is $r2")

    /**
     * def init[S]: State[S, S] = State(s => (s, s))
     * def get[S]: State[S, S] = init
     * And put in this context means to put some value into the state:
     *
     * def put[S](s: S): State[S, Unit] = State(_ => (s, ()))
     */


    def stackyStack: State[Stack, Unit] = for {
      stackNow <- get
      r <- if (stackNow === List(1, 2, 3)) put(List(8, 3, 1) ++ stackNow)
      else put(List(9, 2, 1))
    } yield r

    val r3 = stackyStack(List(1, 2, 3))
    println(s"r3 is $r3")

    // We can also implement pop and push in terms of get and put

//    scala> val pop: State[Stack, Int] = for {
//      s <- get[Stack]
//      val (x :: xs) = s
//      _ <- put(xs)
//    } yield x
//    pop: scalaz.State[Stack,Int] = scalaz.StateT$$anon$7@40014da3
//
//    scala> def push(x: Int): State[Stack, Unit] = for {
//      xs <- get[Stack]
//      r <- put(x :: xs)
//    } yield r
//    push: (x: Int)scalaz.State[Stack,Unit]

  }

}
