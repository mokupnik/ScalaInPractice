package myTraits

abstract class Pluginable {
  def plugin(text: String): String = text

}

trait Reverting extends Pluginable { abstract override def plugin(text: String): String = { super.plugin(text.foldLeft("")((acc, x) => x + acc)) } }

trait LowerCasing extends Pluginable { abstract override def plugin(text: String): String = { super.plugin(text.toLowerCase()) } }

trait SimpleSpacing extends Pluginable { abstract override def plugin(text: String): String = { super.plugin(text.replaceAll("  ", " ")) } }

trait NoSpacing extends Pluginable { abstract override def plugin(text: String): String = { super.plugin(text.replaceAll(" ", "")) } }

trait DuplicateRemoval extends Pluginable { abstract override def plugin(text: String): String = { super.plugin(text.filter((x) => text.lastIndexOf(x) == text.indexOf(x))) } }

trait Doubling extends Pluginable { abstract override def plugin(text: String): String = { super.plugin(text.zipWithIndex.foldLeft("")((acc, c) => { if (c._2 % 2 == 1) { acc + c._1 + c._1 } else { acc + c._1 } })) } }

trait Shortening extends Pluginable { abstract override def plugin(text: String): String = super.plugin(text.zipWithIndex.filterNot(x => x._2 % 2 == 1).map((x) => x._1).foldLeft("")((acc, c) => acc + c)) }

trait Rotating extends Pluginable { abstract override def plugin(text: String): String = super.plugin(text.last + text.substring(0, text.length - 1)) }
