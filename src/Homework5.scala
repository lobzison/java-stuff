object Homework5 extends App {

//  case class Pair[A, B](one: A, two: B)
//
//  val pair = Pair[String, Int]("hi", 2)
//  println(pair)
//
//  sealed trait Sum[A, B] {
//    def fold[C](left: A => C, right: B => C) = {
//      this match {
//        case Left(x) => left(x)
//        case Right(x) => right(x)
//      }
//    }
//  }

//  final case class Left[A, B](value: A) extends Sum[A, B]
//
//  final case class Right[A, B](value: B) extends Sum[A, B]

  println(Left[Int, String](1).value)

  sealed trait Maybe[A] {
    def fold[B](end: B, func: A => B): B = {
      this match {
        case Empty() => end
        case Full(value) => func(value)
      }
    }

    def map[B](func: A => B): Maybe[B] = {
      this match {
        case Empty() => Empty[B]()
        case Full(v) => this.flatMap(x => Full(func(x)))
      }
    }

    def flatMap[B](func: A => Maybe[B]): Maybe[B] = {
      this match {
        case Empty() => Empty[B]()
        case Full(c) => func(c)}
    }
  }

  final case class Full[A](value: A) extends Maybe[A]

  final case class Empty[A]() extends Maybe[A]

  val perhaps: Maybe[Int] = Empty[Int]
  // perhaps: Maybe[Int] = Empty()

  val perhaps2: Maybe[Int] = Full(1)
  // perhaps: Maybe[Int] = Full(1)

  println(perhaps, perhaps2)

  sealed trait LinkedList[A] {
    def fold[B](end: B, func: (A, B) => B): B = {
      this match {
        case End() => end
        case Pair(hd, tl) => func(hd, tl.fold(end, func))
      }
    }

    def map[B](func: A => B):LinkedList[B] = {
      this match {
        case End() => End()
        case Pair(hd, tl) => Pair(func(hd), tl.map(func))
      }
    }
  }
  final case class Pair[A](head: A,tail: LinkedList[A]) extends LinkedList[A]
  final case class End[A]() extends LinkedList[A]

  val list: LinkedList[Int] = Pair(1, Pair(2, Pair(3, End())))

  val doubleList = list.map(_ * 2)
  println(doubleList)
  val plusList = list.map(_ + 1)
  println(plusList)
  val divList = list.map(_ / 3)
  println(divList)

  val list2 = List(1, 2, 3)
  println(list2.flatMap(x => List(x, -x)))

  val list3 = List(Full(3), Full(2), Full(1))

  println(list3.flatMap(x => List(if ((x % 2) == 0) x else None )))

}
