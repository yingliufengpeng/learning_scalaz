package day01

object Pf_Enum {


  def main(args: Array[String]): Unit = {
    import scalaz.Scalaz._

    val r = 'a' to 'e'
    println(s"r is $r")

    val r2 = 'a' |-> 'e'
    println(s"r is $r2")
    println(s"r2' succ is ${r2}")

    val r3 = 3 |=> 5
    println(s"r2 is $r3")

    val r4 = 'b'
    println(s"r4 is $r4")
    println(s"r4' succ is ${r4.succ}")
    println(s"r4' pred is ${r4.pred}")

  }
}
