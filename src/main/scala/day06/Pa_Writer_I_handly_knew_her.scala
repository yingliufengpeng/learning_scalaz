package day06

object Pa_Writer_I_handly_knew_her {

  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

    def isBigGang(x: Int): (Boolean, String) =
      (x > 9, "Compared gang size to 9.")

//    implicit class PairOps[A](pair: (A, String)) {
//      def applyLog[B](f: A => (B, String)): (B, String) = {
//        val (x, log) = pair
//        val (y, newlog) = f(x)
//        (y, log ++ newlog)
//      }
//    }

    implicit class PairOps[A, B: Monoid](pair: (A, B)) {
      def applyLog[C](f: A => (C, B)): (C, B) = {
        val (x, log) = pair
        val (y, newlog) = f(x)
        (y, log |+| newlog)
      }
    }


    val r = (3, "Smallish gang.") applyLog isBigGang
    println(s"r is $r")

    // type Writer[+W, +A] = WriterT[Id, W, A]

    /**
     * sealed trait WriterT[F[+_], +W, +A] { self =>
     * val run: F[(W, A)]
     *
     * def written(implicit F: Functor[F]): F[W] =
     *     F.map(run)(_._1)
     * def value(implicit F: Functor[F]): F[A] =
     *     F.map(run)(_._2)
     * }
     */

    val r2 = 3.set("kkk")
    println(s"r2 is $r2")

    /**
     * scala> 3.set("something")
     * res57: scalaz.Writer[String,Int] = scalaz.WriterTFunctions$$anon$26@159663c3
     *
     * scala> "something".tell
     * res58: scalaz.Writer[String,Unit] = scalaz.WriterTFunctions$$anon$26@374de9cf
     */
    type W[A] = Writer[String, A]
    MonadTell[W, String]

//    scala> MonadTell[W, String]
//    res20: scalaz.MonadTell[W,String] = scalaz.WriterTInstances$$anon$3@3e815a
//
//    scala> MonadTell[W, String].point(3).run
//    res21: scalaz.Id.Id[(String, Int)] = ("",3)

    def logNumber(x: Int): Writer[List[String], Int] =
      x.set(List("Got number: " + x.shows))

    def multWithLog: Writer[List[String], Int] = for {
      a <- logNumber(3)
      b <- logNumber(5)
      b <- logNumber(7)
    } yield a * b

    val r3 = multWithLog.run
    println(s"r3 is $r3")

    def gcd(a: Int, b: Int): Writer[List[String], Int] =
      if (b == 0) for {
        _ <- List("Finished with " + a.shows).tell
      } yield a
      else
        List(a.shows + " mod " + b.shows + " = " + (a % b).shows).tell >>= { _ =>
          gcd(b, a % b)
        }


    val r4 = for {
      _ <- List("Finished with '''").tell
    } yield 4
    println(s"r4 is $r4")

    val r5 = gcd(8, 3).run
    println(s"r5 is $r5")


    def gcd2(a: Int, b: Int): Writer[Vector[String], Int] =
      if (b == 0) for {
        _ <- Vector("Finished with " + a.shows).tell
      } yield a
      else for {
        result <- gcd2(b, a % b)
        _ <- Vector(a.shows + " mod " + b.shows + " = " + (a % b).shows).tell
      } yield result


    val r6 = gcd2(8, 3).run
    println(s"r6 is $r6")

    // comparing preformance
    def vectorFinalCountDown(x: Int): Writer[Vector[String], Unit] = {
      import annotation.tailrec
      @tailrec def doFinalCountDown(x: Int, w: Writer[Vector[String], Unit]): Writer[Vector[String], Unit] = x match {
        case 0 => w >>= { _ => Vector("0").tell }
        case x => doFinalCountDown(x - 1, w >>= { _ =>
          Vector(x.shows).tell
        })
      }
      val t0 = System.currentTimeMillis
      val r = doFinalCountDown(x, Vector[String]().tell)
      val t1 = System.currentTimeMillis
      r >>= { _ => Vector((t1 - t0).shows + " msec").tell }
    }

    def listFinalCountDown(x: Int): Writer[List[String], Unit] = {
      import annotation.tailrec
      @tailrec def doFinalCountDown(x: Int, w: Writer[List[String], Unit]): Writer[List[String], Unit] = x match {
        case 0 => w >>= { _ => List("0").tell }
        case x => doFinalCountDown(x - 1, w >>= { _ =>
          List(x.shows).tell
        })
      }
      val t0 = System.currentTimeMillis
      val r = doFinalCountDown(x, List[String]().tell)
      val t1 = System.currentTimeMillis
      r >>= { _ => List((t1 - t0).shows + " msec").tell }
    }

    val r7 = vectorFinalCountDown(10000).run
    println(s"r7 is ${r7._1.last}")

    val r8 = listFinalCountDown(10000).run
    println(s"r7 is ${r8._1.last}")
  }
}
