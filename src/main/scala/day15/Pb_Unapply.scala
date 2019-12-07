package day15

object Pb_Unapply {

  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

    val r = implicitly[Unapply[Applicative, Int]]
    println(s"r is $r")

    val failedTree: Tree[Validation[String, Int]] = 1.success[String].node(
      2.success[String].leaf, "boom".failure[Int].leaf)

    println(s"failedTree is ${failedTree.sequenceU}")

  }

}
