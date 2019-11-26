package day01

object Pj_Yes_No_Types {

  def main(args: Array[String]): Unit = {

    trait CanTruthy[A] {
      self =>
      /** @return true, if `a` is truthy. */
      def truthys(a: A): Boolean
    }
    object CanTruthy {
      def apply[A](implicit ev: CanTruthy[A]): CanTruthy[A] = ev

      def truthys[A](f: A => Boolean): CanTruthy[A] = new CanTruthy[A] {
        def truthys(a: A): Boolean = f(a)
      }

      implicit lazy val intCanTruthy: CanTruthy[Int] = CanTruthy.truthys({
        case 0 => false
        case _ => true
      })

      //      implicit val intCanTruthy2: CanTruthy[Int] = new CanTruthy[Int] {
      //        /** @return true, if `a` is truthy. */
      //        override def truthys(a: Int): Boolean = a match {
      //          case 0 => false
      //          case _ => true
      //        }
      //      }


      implicit def listCanTruthy[A]: CanTruthy[List[A]] = CanTruthy.truthys({
        case Nil => false
        case _ => true
      })

      implicit lazy val nilCanTruthy: CanTruthy[scala.collection.immutable.Nil.type] =
        CanTruthy.truthys(_ => false)

      implicit lazy val booleanCanTruthy: CanTruthy[Boolean] = CanTruthy.truthys(identity)

    }
    trait CanTruthyOps[A] {
      def self: A

      implicit def F: CanTruthy[A]

      final def truthy: Boolean = F.truthys(self)
    }
    object ToCanIsTruthyOps {
      implicit def toCanIsTruthyOps[A](v: A)(implicit ev: CanTruthy[A]): CanTruthyOps[A] =
        new CanTruthyOps[A] {
          def self: A = v

          implicit def F: CanTruthy[A] = ev
        }
    }


    import CanTruthy._
    import ToCanIsTruthyOps._
    val r = 10.truthy
    println(s"r is $r")

    val r2 = List("foo").truthy
    println(s"r2 is $r2")

    val r3 = Nil.truthy
    println(s"r3 is $r3")

    def truthyIf[A: CanTruthy, B, C](cond: A)(ifyes: => B)(ifno: => C) =
      if (cond.truthy) ifyes
      else ifno

    val r4 = truthyIf(Nil) {
      "YEAH!"
    } {
      "NO!"
    }
    val r5 = truthyIf(2 :: 3 :: 4 :: Nil) {
      "YEAH!"
    } {
      "NO!"
    }
    val r6 = truthyIf(true) {
      "YEAH!"
    } {
      "NO!"
    }
    println(s"r4 is $r4")
    println(s"r5 is $r5")
    println(s"r6 is $r6")
  }
}
