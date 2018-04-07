package daggerok

object App {

  def main(args: Array[String]) {
    println("Hello World!")
    println("fold arguments = " + foleArgs(args))
  }

  def foleArgs(x: Array[String]) = x.foldLeft("")((a, b) => a + b)
}
