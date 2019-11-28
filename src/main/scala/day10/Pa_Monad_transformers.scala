package day10

import scalaz._
import scalaz.Scalaz._

object Pa_Monad_transformers {
  type ReaderTOption[A, B] = ReaderT[Option, A, B]

  object ReaderTOption extends KleisliInstances {
    def apply[A, B](f: A => Option[B]): ReaderTOption[A, B] = Kleisli(f)
  }

  def monadTransformer(): Unit = {


    def myName(step: String): Reader[String, String] = Reader {
      step + ", I am " + _
    }

    def localExample: Reader[String, (String, String, String)] = for {
      a <- myName("First")
      b <- myName("Second") >=> Reader {
        _ + "dy"
      }
      c <- myName("Third")
    } yield (a, b, c)

    val r = localExample
    println(s"""r is ${r.run("Fred")}""")


    def configure(key: String): ReaderTOption[Map[String, String], String] =
      ReaderTOption[Map[String, String], String] {
        _.get(key)
      }

    def setupConnection: ReaderT[Option, Map[String, String], (String, String, String)] = for {
      host <- configure("host")
      user <- configure("user")
      password <- configure("password")
    } yield (host, user, password)

    // 这些是我们的配置文件
    val goodConfig = Map(
      "host" -> "eed3si9n.com",
      "user" -> "sa",
      "password" -> "****"
    )

    val r2 = setupConnection(goodConfig)
    println(s"r2 is $r2")

    val badConfig = Map(
      "host" -> "example.com",
      "user" -> "sa"
    )

    val r3 = setupConnection(badConfig)
    println(s"r3 is $r3")
  }

  def stack_multiple_monad_transformers(): Unit = {

    //    type StateTReaderTOption[C, S, A] = StateT[({type l[X] = ReaderTOption[C, X]})#l, S, A]
    type StateTReaderTOption[C, S, A] = StateT[ReaderTOption[C, ?], S, A]

    object StateTReaderTOption extends StateTInstances with StateTFunctions {
      def apply[C, S, A](f: S => (S, A)): StateT[ReaderTOption[C, ?], S, A] = StateT[ReaderTOption[C, ?], S, A](s => f(s).pure[ReaderTOption[C, ?]])

      def get[C, S]: StateTReaderTOption[C, S, S] =
        StateTReaderTOption { s => (s, s) }

      def put[C, S](s: S): StateTReaderTOption[C, S, Unit] =
        StateTReaderTOption { _ => (s, ()) }
    }

    type Stack = List[Int]
    type Config = Map[String, String]

    val pop: StateTReaderTOption[Config, Stack, Int] = {
      import StateTReaderTOption.{get, put}
      for {
        s <- get[Config, Stack]
        x :: xs = s
        _ <- put(xs)
      } yield x
    }

    def push(x: Int): StateTReaderTOption[Config, Stack, Unit] = {
      import StateTReaderTOption.{get, put}
      for {
        xs <- get[Config, Stack]
        r <- put(x :: xs)
      } yield r
    }

    def stackManip: StateTReaderTOption[Config, Stack, Int] = for {
      _ <- push(3)
      _ <- pop
      b <- pop
    } yield b

    val r = stackManip(List(5, 8, 2, 1)).run(Map())
    println(s"r is $r")

    def configure2(key: String): ReaderTOption[Config, String] =
      ReaderTOption[Config, String] {
        _.get(key)
      }

    //    def configure(key: String): StateTReaderTOption[Config, Stack, String] = StateT.liftM(configure2(key))
    def configure(key: String): StateTReaderTOption[Config, Stack, String] = StateT.liftM[ReaderTOption[Config, ?], Stack, String](configure2(key))

    def stackManip2: StateTReaderTOption[Config, Stack, Unit] = for {
      x <- configure("x")
      a <- push(x.toInt)
    } yield a

    val r2 = stackManip2(List(5, 8, 2, 1)).run(Map("x" -> "7"))
    println(s"r2 is $r2")

    val r3 = stackManip2(List(5, 8, 2, 1)).run(Map("y" -> "7"))
    println(s"r2 is $r3")

  }

  def main(args: Array[String]): Unit = {

    //    monadTransformer()

    stack_multiple_monad_transformers()
  }


}
