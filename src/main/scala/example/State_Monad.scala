package example

object State_Monad {


  def main(args: Array[String]): Unit = {

    case class State[S, A](f: S => (S, A)) {
      def apply(s: S): (S, A) = f(s)

      def map[B](f2: A => B): State[S, B] = State[S, B](
        s => {
          val (s1, a) = f(s)
          (s1, f2(a))
        }
      )

      def flatMap[B](f2: A => State[S, B]): State[S, B] = State[S, B] (
        s => {
          val (s1, a) = f(s)
          val state = f2(a)
          state(s1)
        }
      )

    }

    object State {

      def pure[S, A](a: A): State[S, A] = State[S, A] (s => (s, a))

      def put[S](s: S): State[S, Unit] = State[S, Unit](s => (s, ()))

      def init[S](s: S): State[S, S] = State[S, S](s => (s, s))
    }

    val s1 = State.pure[String, Int](3)
    val s2 = State.pure[String, Int](4)

    val s3 = for {
      a <- s1
      b <- s2
    } yield a + b

    println(s"s3 is ${s3("")}")

  }
}
