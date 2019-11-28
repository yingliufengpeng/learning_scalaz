package day09

object Pb_Zipper {

  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

    /**
     * scala> Stream(1, 2, 3, 4).toZipper >>= {_.next}
     * res25: Option[scalaz.Zipper[Int]] = Some(Zipper(<lefts>, 2, <rights>))
     *
     * scala> Stream(1, 2, 3, 4).toZipper >>= {_.next} >>= {_.next}
     * res26: Option[scalaz.Zipper[Int]] = Some(Zipper(<lefts>, 3, <rights>))
     *
     * scala> Stream(1, 2, 3, 4).toZipper >>= {_.next} >>= {_.next} >>= {_.previous}
     * res27: Option[scalaz.Zipper[Int]] = Some(Zipper(<lefts>, 2, <rights>))
     */

    val r = for {
      z <- Stream(1, 2, 3, 4).toZipper
      n1 <- z.next
      n2 <- n1.next
    } yield { n2.modify {_ => 7} }
    println(s"r is ${r.get.toList}")
  }

}
