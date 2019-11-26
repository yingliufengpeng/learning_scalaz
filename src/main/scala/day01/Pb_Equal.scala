package day01

object Pb_Equal {


  def main(args: Array[String]): Unit = {
    import scalaz.Scalaz._
    val r = 1 === 1
    println(s"r is $r")

//    val r2 = 1 === "foo"
//    println(s"r2 is $r2")

    val r3 = 1.some =/= 2.some
    println(s"r3 is $r3")

    /**
     * Normally comparison operators like != have lower higher precedence
     * than &&, all letters, etc. Due to special precedence rule /== is recognized
     * as an assignment operator because it ends with = and does not start with =,
     * which drops to the bottom of the precedence:  使当前的优先级降级!!!
     */

  }
}
