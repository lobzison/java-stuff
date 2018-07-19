println("HW")
import scala.annotation.tailrec
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
    merge(mergesort2 (vectors._1), mergesort2(vectors._2))
  }
}
println(mergesort2(Vector(6,1,1,2,5,7,8,9,4,535,1,32,5,7,8,345,1,32,4,6,587,24,213,4,2,6,567)))



def mergesort3(vec: Vector[Int]): Vector[Int] = {
  def merge(first:Vector[Int], second:Vector[Int]):Vector[Int] = {
    @tailrec
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
    merge(mergesort3 (vectors._1), mergesort3(vectors._2))
  }
}

println(mergesort3(Vector(6,1,1,2,5,7,8,9,4,535,1,32,5,7,8,345,1,32,4,6,587,24,213,4,2,6,567)))

def time[R](block: => R) = {
    val t0 = System.nanoTime()
    val result = block    // call-by-name
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) + "ns")
    result
}

val r =  scala.util.Random
val max = 10000

def createRandomVector(size: Int):Vector[Int] = {
  def aux(acc:Vector[Int]):Vector[Int] = {
    if (acc.size >= size) { acc }
    else {aux (acc :+ r.nextInt(max))}
  }
  aux(Vector())
}

val v = createRandomVector(10000)

time {mergesort(v)}  // Elapsed time: 233399999ns
time {mergesort2(v)} // Elapsed time: 183700001ns
time {mergesort3(v)} // Elapsed time: 257400000ns
//WTF

def mergesort4(vec: Vector[Int]): Vector[Int] = {
  @tailrec
  def merge(acc:Vector[Int], first:Vector[Int], second:Vector[Int]):Vector[Int] = {
        (first, second) match {
          case (fHead+:fTail, sHead+:sTail) => if (fHead < sHead) {merge(acc :+ fHead, fTail, second)}
                                               else {merge(acc :+ sHead, first, sTail)}
          case (_+:_,_) => acc ++ first
          case (_,_+:_) => acc ++ second
        }
  }
  if (vec.size <= 1) {vec}
  else {
    val vectors = vec splitAt (vec.size / 2)
    merge(Vector(), mergesort4 (vectors._1), mergesort4(vectors._2))
  }
}
