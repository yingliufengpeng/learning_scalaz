package day17

import scalaz.effect.IO

object Pa_IO_Monad {

  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._
    import scalaz.effect.IO._

    val action1 = for {
      _ <- putStrLn("Hello, world!")
    } yield ()

    val action2 = IO {
      val source = scala.io.Source.fromFile("./README.md")
      source.getLines.toStream
    }

    def program: IO[Unit] = for {
      line <- readLn
      _    <- putStrLn(line)
    } yield ()

//    program.unsafePerformIO()

    (program |+| program).unsafePerformIO
  }
}
