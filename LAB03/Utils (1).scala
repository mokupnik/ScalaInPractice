
object Utils {
  def isSorted(as: List[Int], ordering: (Int, Int) => Boolean): Boolean = {
    as match {
      case Nil => true
      case x :: Nil => true
      case x :: y :: xs => ordering(x, y) && isSorted(y :: xs, ordering)
    }

  }

  def isAscSorted(as: List[Int]): Boolean = {
    val fun = (x: Int, y: Int) => x <= y

    isSorted(as, fun)

  }

  def isDescSorted(as: List[Int]): Boolean = {
    val fun = (x: Int, y: Int) => x >= y

    isSorted(as, fun)

  }

  def foldLeft[A, B](l: List[A], z: B)(f: (B, A) => B): B = {
    l match {

      case Nil => z
      case x :: Nil => f(z, x)

      case l :: ls => foldLeft(ls, f(z, l))(f)

    }

  }

  def sum(l: List[Int]): Int = {
    val add = (x: Int, y: Int) => x + y
    foldLeft(l.tail, l.head)(add)


  }

  def length[A](l: List[A]): Int = {
    val len = (y: Int, x: A) => {
      x match {
        case Nil => 0

        case _ => y + 1
      }
    }

    foldLeft(l, 0)(len)


  }

  def compose[A, B, C](f: A => B)(g: C => A)(x: C): B = {
    f(g(x))
  }

  def repeated[A,B](f: Int => Int)(n: Int): Int = {
    def helper(f: Int => Int)(n: Int)(x: Int): Int = {
      if (x == 1) {
        f(n)

      }
      else {

        f(helper(f)(n)(x-1))
      }
    }

    helper(f)(n)(n)

  }


  def curry[A, B, C](f: (A, B) => C): A => B => C = { (x: A) => { (y: B) => f(x, y) } }

  def uncurry[A, B, C](f: A => B => C): (A, B) => C = { (x: A, y: B) => f(x)(y) }

  def unSafe[T](ex: Exception)(block: => T): T = {
    try {
      block
    } catch {
      case e: Throwable => throw ex
    }
  }
}
object Main {

  // Main method
  def main(args: Array[String]) {

    // Class object
    val fun = (x: Int, y: Int) => x < y
    val dec = (x: Int) => x - 1
    val append = (x: Int, y: Int) => List(x, y)

    require(Utils.isAscSorted(List(1, 2, 3)) == true)
    require(Utils.isDescSorted(List(3, 2, 0)) == true)
    require(Utils.sum((List(1, 2, 3, 4))) == 10)
    require(Utils.length(List(1, 2, 3, 4)) == 4)
    require(Utils.repeated(dec)(4) == 0)
    require(Utils.curry(append)(2)(1) == List(2, 1))
    require(Utils.uncurry(Utils.curry(append))(1, 2) == List(1, 2))


    val block = { (x: Int) => {

      x / 0

    }
    }


  }
}