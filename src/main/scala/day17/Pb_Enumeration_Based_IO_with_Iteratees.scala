package day17

import java.io.{BufferedReader, FileReader}

import scalaz.effect.{IO, IoExceptionOr}
import scalaz.iteratee.{Input, Iteratee}

import scala.reflect.io.File

object Pb_Enumeration_Based_IO_with_Iteratees {


  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._
    import scalaz.effect.IO._
    import iteratee._
    import Iteratee._

    def counter[E]: Iteratee[E, Int] = {
      def step(acc: Int)(s: Input[E]): Iteratee[E, Int] =
        s(el = e => cont(step(acc + 1)),
          empty = cont(step(acc)),
          eof = done(acc, eofInput[E])
        )
      cont(step(0))
    }

    val r = (counter[Int] &= enumerate(Stream(1, 2, 3))).run
    println(s"r is $r")

    val r2 = (length[Int, Id] &= enumerate(Stream(1, 2, 3))).run
    println(s"r2 is $r2")

    def drop1Keep1[E]: Iteratee[E, Option[E]] = for {
      _ <- drop[E, Id](1)
      x <- head[E, Id]
    } yield x

    def alternates[E]: Iteratee[E, Stream[E]] =
      repeatBuild[E, Option[E], Stream](drop1Keep1) map {_.flatten}

    val r3 = (alternates[Int] &= enumerate(Stream.range(1, 15))).run.force
    println(s"r3 is $r3")

//    def lengthOfTwoFiles(f1: File, f2: File) = {
//      val l1 = length[IoExceptionOr[Char], IO] &= enumReader[IO](new BufferedReader(new FileReader(f1)))
//      val l2 = l1 &= enumReader[IO](new BufferedReader(new FileReader(f2)))
//      l2.run
//    }
//
//    lengthOfTwoFiles(new File("./README.md"), new File("./TODO.txt")).unsafePerformIO

    val readLn = takeWhile[Char, List](_ != '\n') flatMap (ln => drop[Char, Id](1).map(_ => ln))

    val r4 = (readLn &= enumStream("Iteratees\nare\ncomposable".toStream)).run
    println(s"r4 is $r4")

    val r5 = (collect[List[Char], List] %= readLn.sequenceI &= enumStream("Iteratees\nare\ncomposable".toStream)).run
    println(s"r5 is $r5")

  }
}
