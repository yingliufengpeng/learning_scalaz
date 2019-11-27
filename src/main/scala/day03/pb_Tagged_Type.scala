package day03

object pb_Tagged_Type {

  def main(args: Array[String]): Unit = {

//    type Tagged[U] = { type Tag = U }
//    type @@[T, U] = T with Tagged[U]
    import scalaz._
    import scalaz.Scalaz._
    sealed trait KiloGram
    def KiloGram[A](a: A): A @@ KiloGram = Tag[A, KiloGram](a)

    val mass = KiloGram(20.0)

    val r = 2 * Tag.unwrap(mass)

    val r2 = 2 * Tag.unwrap(mass)
    println(s"r is $r2")

//    val r3 = mass * 2
//    println(s"r3 is $r3")

    sealed trait JoulePerKiloGram
    def JoulePerKiloGram[A](a: A): A @@ JoulePerKiloGram = Tag[A, JoulePerKiloGram](a)
    def energyR(m: Double @@ KiloGram): Double @@ JoulePerKiloGram =
      JoulePerKiloGram(299792458.0 * 299792458.0 * Tag.unwrap(m))

    val r4 = energyR(mass)
    println(s"r4 is $r4")

  }

}
