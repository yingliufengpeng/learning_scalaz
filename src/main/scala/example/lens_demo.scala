package example

import org.scalacheck.Prop.True

object lens_demo {


  def main(args: Array[String]): Unit = {

    case class Lens[A, B](
                           g: A => B,
                           s: (A, B) => A
                         ) {

      def get(a: A): B = g(a)

      def set(a: A, b: B): A = s(a, b)

      def mod(f: B => B, a: A): A = set(a, f(get(a)))

      def andThen[C](l: Lens[B, C]): Lens[A, C] = Lens[A, C](
        a => l.get(get(a)),
        //        (a, c) => set(a, l.set(get(a), c))
        (a, c) => mod(b => l.set(b, c), a)
      )

      def compose[C](that: Lens[C, A]): Lens[C, B] = that andThen this


    }

    def trivial[A]: Lens[A, Unit] = Lens[A, Unit](
      a => (),
      (a, _) => a
    )

    def self[A]: Lens[A, A] = Lens[A, A](
      a => a,
      (a, _) => a
    )

    def fst[A, B]: Lens[(A, B), A] = Lens[(A, B), A](
      p => p._1,
      (p, a) => p.copy(_1 = a)
    )

    def snd[A, B]: Lens[(A, B), B] = Lens[(A, B), B](
      p => p._2,
      (p, b) => p.copy(_2 = b)
    )

    //    val foo = (1, (2, 3))
    //    val bar = snd.andThen(snd).set(foo, 4 )
    //    bar
    def contains[K](k: K): Lens[Set[K], Boolean] = Lens[Set[K], Boolean](
      _.contains(k),
      {
        case (s, true) => s + k
        case (s, false) => s - k
      }
    )

    def member[K, V](k: K): Lens[Map[K, V], Option[V]] = Lens[Map[K, V], Option[V]] (
      _.get(k),
      {
        case (m, Some(v)) => m + (k -> v)
        case (m, None) => m - k
      }
    )

  }
}
