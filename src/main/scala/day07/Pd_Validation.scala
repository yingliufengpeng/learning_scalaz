package day07

object Pd_Validation {


  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

    /**
     * scala> "event 1 ok".success[String]
     * res36: scalaz.Validation[String,String] = Success(event 1 ok)
     *
     * scala> "event 1 failed!".failure[String]
     * res38: scalaz.Validation[String,String] = Failure(event 1 failed!)
     */

    /**
     * scala> ("event 1 ok".success[String] |@| "event 2 failed!".failure[String] |@| "event 3 failed!".failure[String]) {_ + _ + _}
     * res44: scalaz.Unapply[scalaz.Apply,scalaz.Validation[String,String]]{type M[X] = scalaz.Validation[String,X]; type A = String}#M[String] = Failure(event 2 failed!event 3 failed!)
     *
     */

    "event 1 ok".successNel[String]

    /**
     * scala> "event 1 ok".successNel[String]
     * res48: scalaz.ValidationNEL[String,String] = Success(event 1 ok)
     *
     * scala> "event 1 failed!".failureNel[String]
     * res49: scalaz.ValidationNEL[String,String] = Failure(NonEmptyList(event 1 failed!))
     *
     * scala> ("event 1 ok".successNel[String] |@| "event 2 failed!".failureNel[String] |@| "event 3 failed!".failureNel[String]) {_ + _ + _}
     * res50: scalaz.Unapply[scalaz.Apply,scalaz.ValidationNEL[String,String]]{type M[X] = scalaz.ValidationNEL[String,X]; type A = String}#M[String] = Failure(NonEmptyList(event 2 failed!, event 3 failed!))
     */
  }
}
