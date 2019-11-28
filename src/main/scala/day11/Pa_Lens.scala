package day11

object Pa_Lens {

  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

    case class Point(x: Double, y: Double)
    case class Color(r: Byte, g: Byte, b: Byte)
    case class Turtle(position: Point, heading: Double, color: Color) {
      def forward(dist: Double): Turtle =
        copy(position =
          position.copy(
            x = position.x + dist * math.cos(heading),
            y = position.y + dist * math.sin(heading)
          ))
    }

    val turtlePosition = Lens.lensu[Turtle, Point] (
      (a, value) => a.copy(position = value),
      _.position
    )

    val pointX = Lens.lensu[Point, Double] (
      (a, value) => a.copy(x = value),
      _.x
    )


    val turtleX = turtlePosition >=> pointX

    val t0 = Turtle(Point(2.0, 3.0), 0.0,
      Color(255.toByte, 255.toByte, 255.toByte))
    println(s"t0 is $t0")
    val r = turtleX.get(t0)
    println(s"r is $r")

    val r2 = turtleX.set(t0, 5.0)
    println(s"r2 is $r2")

    val t1 = turtleX.mod(_ + 1.0, t0)
    println(s"t1 is $t1")

    val incX = turtleX =>= {_ + 1.0}
    val r3 = incX(t0)
    println(s"r3 is $r3")

    val incX2 = for {
      x <- turtleX %= {_ + 1.0}
    } yield x

    val r4 = incX2(t0)
    println(s"r4 is $r4")

    val turtleHeading = Lens.lensu[Turtle, Double] (
      (a, value) => a.copy(heading = value),
      _.heading
    )

    val pointY = Lens.lensu[Point, Double] (
      (a, value) => a.copy(y = value),
      _.y
    )

    val turtleY = turtlePosition >=> pointY

    def forward(dist: Double): IndexedState[Turtle, Turtle, (Double, Double)] = for {
      heading <- turtleHeading
      x <- turtleX += dist * math.cos(heading)
      y <- turtleY += dist * math.sin(heading)
    } yield (x, y)

    val r5 = forward(10.0) apply t0
    val r6 = forward(10.0) exec t0
    println(s"r5 is $r5, r6i is $r6")
  }

}
