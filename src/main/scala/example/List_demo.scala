package example

object List_demo {

  def main(args: Array[String]): Unit = {
    import scalaz.Scalaz._
    val list1: List[Int] = List(1, 2, 3)
//    list1: List[Int] = List(1, 2, 3)

    val list2: List[Int] = List(1, 2, 3)
//    list2: List[Int] = List(1, 2, 3)

    list1 === list2
//    res0: Boolean = true

    list2.map(_ + 2)
//    res1: List[Int] = List(3, 4, 5)
  }
}
