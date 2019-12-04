package day21

object Pc_Natural_Transformation {

  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

    val toList = new (Option ~> List) {
      def apply[T](opt: Option[T]): List[T] =
        opt.toList
    }
  }
}
