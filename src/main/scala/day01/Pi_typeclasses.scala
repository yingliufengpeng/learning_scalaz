package day01

import scalaz._
import scalaz.Scalaz._

object Pi_typeclasses {

  def main(args: Array[String]): Unit = {

//    sealed trait TrafficLight
//    case object Red extends TrafficLight
//    case object Yellow extends TrafficLight
//    case object Green extends TrafficLight
//
//    implicit val TrafficLightEqual: Equal[TrafficLight] = Equal.equal(_ == _)
//
//    val r = Red === Red

    case class TrafficLight(name: String)
    val red = TrafficLight("red")
    val yellow = TrafficLight("yellow")
    val green = TrafficLight("green")
    implicit val TrafficLightEqual: Equal[TrafficLight] = Equal.equal(_ == _)
    val r2 = red === yellow
    println(s"r2 is $r2")


  }
}
