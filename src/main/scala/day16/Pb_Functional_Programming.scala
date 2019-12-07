package day16

object Pb_Functional_Programming {

  def main(args: Array[String]): Unit = {

    import scalaz._
    import scalaz.Scalaz._

    /**
     * What is functional programming? Rúnar Óli defines it as:
     *
     * programming with functions.
     *
     * What’s a function?
     *
     * f: A => B relates every value of type of A to excatly one value of type B and nothing else.
     *
     * To clarify the “nothing else” part, he introduces the notion of referential transparency as follows:
     *
     * An expression e is referentially transparent if every occurrence e can be replaced with its value without
     * affecting the observable result of the program.
     *
     * Using this notion, we can think of functional programming as building up referentially transparent expression
     * tree. Memoization is one way of taking the advantage of referential transparency.
     */
  }
}
