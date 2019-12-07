package day16

object Pc_Effect_system {


  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._
    import effect._
    import ST.{newVar, runST, newArr, returnST}

    def e1[S]: ST[S, STRef[S, Int]] = for {
      x <- newVar[S](0)
      _ <- x mod {
        _ + 1
      }
    } yield x

    def e2[S]: ST[S, Int] = for {
      x <- e1[S]
      r <- x.read
    } yield r

    //    type ForallST[A] = Forall[ST[?, A]]

    val r = runST(new Forall[ST[?, Int]] {
      override def apply[S]: ST[S, Int] = e2[S]
    })
    println(s"r is $r")


    def mapM[A, S, B](xs: List[A])(f: A => ST[S, B]): ST[S, List[B]] =
      Monad[ST[S, ?]].sequence(xs map f)

    def sieve[S](n: Int): ST[S, ImmutableArray[Boolean]] = for {
      arr <- newArr[S, Boolean](n + 1, true)
      _ <- arr.write(0, false)
      _ <- arr.write(1, false)
      nsq = (math.sqrt(n.toDouble).toInt + 1)
      _ <- mapM(1 |-> nsq) { i =>
        for {
          x <- arr.read(i)
          _ <-
            if (x) mapM(i * i |--> (i, n)) { j => arr.write(j, false) }
            else returnST[S, List[Boolean]] {
              Nil
            }
        } yield ()
      }
      r <- arr.freeze
    } yield r

    type ForallST[A] = Forall[ST[?, A]]

    def prime(n: Int): Array[Int] =
      runST(new ForallST[ImmutableArray[Boolean]] {
        def apply[S]: ST[S, ImmutableArray[Boolean]] = sieve[S](n)
      }).toArray.
        zipWithIndex collect { case (true, x) => x }

    val r2 = prime(1000)
    println(s"r2 is ${r2.toList}")
  }
}
