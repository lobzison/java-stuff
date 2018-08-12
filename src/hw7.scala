object hw7 extends App {
  def addOption(one: Option[Int], other: Option[Int]): Option[Int] = {
    for {
      x <- one
      y <- other
    } yield x + y
  }

  def addOptionMap(one: Option[Int], other: Option[Int]): Option[Int] = {
    one.flatMap(x => other.map(_ + x))
  }

  println(addOptionMap(Some[Int](1), None))

  def addOption(one: Option[Int], other: Option[Int], other2: Option[Int]): Option[Int] = {
    for {
      x <- one
      y <- other
      z <- other2
    } yield x + y + z
  }

  def addOptionMap(one: Option[Int], other: Option[Int], other2: Option[Int]): Option[Int] = {
    one.flatMap(x => other.flatMap(y => other2.map(_ + x + y)))
  }

  def divide(divident: Int, divisor: Int): Option[Int] = {
    if (divisor == 0) None else Some(divident/divisor)
  }

  def divideOptions(divident: Option[Int], divisor: Option[Int]): Option[Int] = {
    for {
      x <- divident
      y <- divisor
      z <- divide(x, y)
    } yield z
  }

  def calculator(operand1: String, operator: String, operand2: String): Unit = {
    operator match {
      case 
    }
  }
}
