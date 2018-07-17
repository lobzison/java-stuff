println("HW")

def myMethod (x: String, y: String, z: String = "default") = {
  println(s"$x and $y and $z")
}

myMethod(y = "one", x = "two")

class Stack[A] {
  private var elements: List[A] = Nil
  def push(elem: A) = elements = elem :: elements
  def pop():A = {
    val currentTop = elements.head
    elements = elements.tail
    currentTop
  }
  def peek():A = elements.head
}

val myThing = new Stack[String]

myThing.push("Ha-ha")
println(myThing peek)

def mergesort(vec: Vector[Int]): Vector[Int] = {
  def merge(first:Vector[Int], second:Vector[Int]):Vector[Int] = {
    if (first.isEmpty) {return second}
    if (second.isEmpty) {return first}
    if (first(0) < second(0)) {
      first(0) +: merge(first.tail, second)
    }
    else second(0) +: merge(first, second.tail)
  }
  if (vec.size <= 1) {vec}
  else {
    val vectors = vec splitAt (vec.size / 2)
    merge(mergesort (vectors._1), mergesort(vectors._2))
  }
}

println(mergesort(Vector(6,1,1,2,5,7,8,9,4,535,1,32,5,7,8,345,1,32,4,6,587,24,213,4,2,6,567)))

def merge(first:Vector[Int], second:Vector[Int]):Vector[Int] = {
  println(first.isEmpty)
  println(second.isEmpty)
  if (first.isEmpty) {return second}
  if (second.isEmpty) {return first}
  if (first(0) < second(0)) {
    first(0) +: merge(first.tail, second)
  }
  else second(0) +: merge(first, second.tail)
}

println(merge(Vector(1), Vector()))
val test = Vector(1)
val test2  = Vector()
println(test(0) +:test2)



def mergesort2(vec: Vector[Int]): Vector[Int] = {
  def merge(first:Vector[Int], second:Vector[Int]):Vector[Int] = {
    (first.isEmpty, second.isEmpty) match {
      case (true,_) => second
      case (_,true) => first
      case _ => if (first(0) < second(0)) {
                  first(0) +: merge(first.tail, second)
                }
                else second(0) +: merge(first, second.tail)
                }
  }
  if (vec.size <= 1) {vec}
  else {
    val vectors = vec splitAt (vec.size / 2)
    merge(mergesort (vectors._1), mergesort(vectors._2))
  }
}
println(mergesort2(Vector(6,1,1,2,5,7,8,9,4,535,1,32,5,7,8,345,1,32,4,6,587,24,213,4,2,6,567)))



def mergesort3(vec: Vector[Int]): Vector[Int] = {
  def merge(first:Vector[Int], second:Vector[Int]):Vector[Int] = {
    def aux(acc:Vector[Int], first:Vector[Int], second:Vector[Int]):Vector[Int] = {
        (first.isEmpty, second.isEmpty) match {
        case (true,_) => acc ++ second
        case (_,true) => acc ++ first
        case _ => if (first(0) < second(0)) {
                    aux(acc :+ first(0), first.tail, second)
                  }
                  else aux(acc :+ second(0), first, second.tail)
                  }
    }
   aux(Vector(), first, second) 
  }
  if (vec.size <= 1) {vec}
  else {
    val vectors = vec splitAt (vec.size / 2)
    merge(mergesort (vectors._1), mergesort(vectors._2))
  }
}

println(mergesort3(Vector(6,1,1,2,5,7,8,9,4,535,1,32,5,7,8,345,1,32,4,6,587,24,213,4,2,6,567)))



