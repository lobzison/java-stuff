// Start writing your ScalaFiddle code here
// case class Person(firstName: String, lastName: String) {
//   def name = s"$firstName $lastName"
// }

// object Person {
//   def apply(name: String):Person = {
//     val parts = name.split(" ")
//     new Person(parts(0), parts(1))
//   }
// }


// val myPerson = Person("Ivan", "Ivanch")
// val myPerson2 = Person("Pendos Jackovich")

// println(myPerson.name)
// println(myPerson2.name)

// object Kazachok {
//   def inspect(person: Person):String = {
//     person match {
//       case Person("Pendos", "Jackovich") =>
//       "Ty cho, ahuel?"
//       case Person(first, _) => s"O, komrad $first"
//     }
//   }
// }


// println(Kazachok.inspect(myPerson))
// println(Kazachok.inspect(myPerson2))


// trait Feline {
//   def colour: String
//   def sound: String
// }

// trait BigCat {
//   def sound: String = "roar"
// }

// case class Cat(colour: String, food: String) extends Feline {
//   val sound: String = "meow"
// }

// case class Lion(colour: String, maneSize: Int) extends BigCat

// case class Tiger(colour: String) extends BigCat

// case class Panther(colour: String) extends BigCat

// val my_cat = Cat("black", "shit")
// println(my_cat.sound)

// trait Shape {
//   def sides: Int
//   def perimeter: Double
//   def area: Double
// }

final case class Box[A](value: A)

val box1: Box[Int] = Box(1)

val box2: Box[String] = Box("string")

println(box1, box2)

// def generic[A[T]](a: A[T]): A[T] = a

// println(generic(box2))

sealed trait Result[A]
final case class Success[A](value: A) extends Result[A]
final case class Fail[A](reason: String) extends Result[A]

sealed trait LinkedList[A] {
  def length: Int = {
    this match {
      case End() => 0
      case Pair(_, tail) => 1 + tail.length 
    }
  }
  def apply(index: Int):Result[A] = {
    (this, index) match {
      case (Pair(head, _), 0) => Success(head)
      case (End(), _) => Fail("OwO opsie woopsie! we did a litte fukko-wokko!")
      case (Pair(_, tail), idx) => tail(idx-1)
    }
  }
  def contains(comp: A):Boolean = {
    this match {
      case End() => false
      case Pair(hd, tail) => (hd == comp) || (tail.contains(comp))
      //will compiler optimise it and stop calls as soon as we hit a ture in first expression?
    }
  }
}
final case class End[A]() extends LinkedList[A]
final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]


val example = Pair(1, Pair(2, Pair(3, End())))
println(example.length)
println(example.tail.length)
println(End().length)

println(example.contains(3))

println(example.contains(4))

println(example.contains(0))
//println(example.contains("Not an int"))

println(example(0))
println(example(1))
println(example(2))
println(example(3))
