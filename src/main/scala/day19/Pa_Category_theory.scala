package day19

object Pa_Category_theory {

  def main(args: Array[String]): Unit = {

    /**
     * Before giving a precise definition of ‘category’, we should become
     * with one example, the category of finite sets and maps. An object
     * in this category is a finite set or collection. … You are probably
     * familiar with some notations for finite sets:
     */

    sealed trait Person {}
    case object John extends Person {}
    case object Mary extends Person {}
    case object Sam extends Person {}

    val a: Set[Person] = Set[Person](John, Mary, Sam)

    sealed trait Breakfast {}
    case object Eggs extends Breakfast {}
    case object Oatmeal extends Breakfast {}
    case object Toast extends Breakfast {}
    case object Coffee extends Breakfast {}

    val favoriteBreakfast: Person => Breakfast = {
      case John => Eggs
      case Mary => Coffee
      case Sam  => Coffee
    }

    /**
     * An arrow in which the domain and codomain are the same
     * object is called an endomorphism(自同态).
     */
    val favoritePerson: Person => Person = {
      case John => Mary
      case Mary => John
      case Sam  => Mary
    }

    /**
     * Data for a category consists of the four ingredients:
     *
     * objects: A, B, C, …
     * arrows: f: A => B
     * identity arrows: 1A: A => A
     * composition of arrows
     * These data must satisfy the following rules:
     *
     * The identity laws:
     *
     * If 1A: A => A, g: A => B, then g ∘ 1A = g
     * If f: A => B, 1B: B => B, then 1A ∘ f = f
     * The associative law:
     *
     * If f: A => B, g: B => C, h: C => D, then h ∘ (g ∘ f) = (h ∘ g) ∘ f
     */
  }

}
