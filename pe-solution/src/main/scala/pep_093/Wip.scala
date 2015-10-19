package pep_093

object Wip {

  object Attempt1 {

    trait IntOp {
      def op(a: Int, b: Int): Option[Int]

      def apply(a: Int, b: Int): Option[Int] = op(a, b)

      def isCommutative: Boolean // TODO remove?
    }

    case object Plus extends IntOp {
      override def op(a: Int, b: Int): Option[Int] = Option(a + b)

      override def isCommutative: Boolean = true
    }

    case object Minus extends IntOp {
      override def op(a: Int, b: Int): Option[Int] = if (a > b) Option(a - b) else None

      override def isCommutative: Boolean = false
    }

    case object Multiply extends IntOp {
      override def op(a: Int, b: Int): Option[Int] = Option(a * b)

      override def isCommutative: Boolean = true
    }

    case object Divide extends IntOp {
      override def op(a: Int, b: Int): Option[Int] = if (a % b == 0) Option(a / b) else None

      override def isCommutative: Boolean = false
    }

    def evalOperations(a: Int, b: Int): Iterator[Int] = List(
      Plus.op(a, b), Minus.op(a, b), Multiply.op(a, b), Divide.op(a, b)
    ).flatten.toIterator

    val operations = List(
      Plus.op _, Minus.op _, Multiply.op _, Divide.op _
    ).toIterator

    /*
        abcd
        ..
          .
           .

         ..
        .
           .

         ..
           .
        .

          ..
         .
        .

        ..
          ..
        ....
     */
    def eval(a: Int, ab: (Int, Int) => Option[Int], b: Int, bc: (Int, Int) => Option[Int], c: Int, cd: (Int, Int) => Option[Int], d: Int): Iterator[Int] = List(
      for {x <- ab(a, b); y <- bc(x, c); z <- cd(y, d)} yield z,
      for {x <- bc(b, c); y <- ab(a, x); z <- cd(y, d)} yield z,
      for {x <- bc(b, c); y <- cd(x, d); z <- ab(a, y)} yield z,
      for {x <- cd(c, d); y <- bc(b, x); z <- ab(a, y)} yield z,
      for {x <- ab(a, b); y <- cd(c, d); z <- bc(x, y)} yield z
    ).flatten.toIterator


    val permutations = Array(1, 2, 3, 4).permutations.map { case Array(a, b, c, d) =>
      (a, b, c, d)
    } //.flatMap(???)

    def solve() = {
      val aaa = for {
        (a, b, c, d) <- permutations
        ab <- operations
        bc <- operations
        cd <- operations
      } yield eval(a, ab, b, bc, c, cd, d)

      aaa foreach println
    }

  }

  object Attempt2 {

    val permutations = Array(1, 2, 3, 4).permutations.map { case Array(a, b, c, d) => (a, b, c, d) }

    // -----------------------------------------------------------------------------------------------------------------

    import scalaz.syntax.std.boolean._

    type Op = (Int, Int) => Option[Int]

    val plus: Op = {
      case (a, b) => Option(a + b)
    }
    val minus: Op = {
      case (a, b) => (a > b) ? Option(a - b) | None
    }
    val multiply: Op = {
      case (a, b) => Option(a * b)
    }
    val divide: Op = {
      case (a, b) => (a % b == 0) ? Option(a / b) | None
    }

    // -----------------------------------------------------------------------------------------------------------------

    def eval(a: Int, ab: Op, b: Int, bc: Op, c: Int, cd: Op, d: Int): Iterator[Int] = List(
      for {x <- ab(a, b); y <- bc(x, c); z <- cd(y, d)} yield z,
      for {x <- bc(b, c); y <- ab(a, x); z <- cd(y, d)} yield z,
      for {x <- bc(b, c); y <- cd(x, d); z <- ab(a, y)} yield z,
      for {x <- cd(c, d); y <- bc(b, x); z <- ab(a, y)} yield z,
      for {x <- ab(a, b); y <- cd(c, d); z <- bc(x, y)} yield z
    ).flatten.toIterator

    // -----------------------------------------------------------------------------------------------------------------

    def solve() = {
      val aaa = for {
        (a, b, c, d) <- permutations
        ab <- Iterator(plus, minus, multiply, divide)
        bc <- Iterator(plus, minus, multiply, divide)
        cd <- Iterator(plus, minus, multiply, divide)
        result <- eval(a, ab, b, bc, c, cd, d)
      } yield result

      val l = aaa.toSet.toList.sorted
      val highest = l.zipWithIndex dropWhile (x => x._1 - x._2 == 1) map (_._2) take 1
      // TODO maximize
      //Iterator.from(1)
      l.mkString("\n")
    }
  }

}
