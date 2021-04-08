
import org.scalatest.funsuite.AnyFunSuite
import myTraits.Actions._

class testActions extends AnyFunSuite {
  val text = "a  B  c"
  test("Action A works") {
    assert(actionA.plugin(text) == "a  c") // "a b c" => "a  b  c" => "a  c"
  }
  test("Action B works") {
    assert(actionB.plugin(text) == "acc") // "abc" => "ac" => "acc"
  }
  test("Action C works") {
    assert(actionC.plugin(text) == "a   bb   c") // "a  b  c" =>>"a   bb   c"
  }

  test("Action D works ") {
    assert(actionD.plugin(text) == "caB") // "aBc" => "caB"
  }

  test("Action E works") {
    assert(actionE.plugin(text) == "cca") // "aBc" => "ac" => "acc" => "caa"
  }

  test("Action F works") {
    assert(actionF.plugin(text) == " B  ca ") // after first rotation  "ca  B  "=>next two rotations we put two whitespaces in the front, next we push B in the front, then another whitespace
  }
  test("Action G works") {
    assert(actionG.plugin(text) == actionB.plugin(actionA.plugin(text))) // i studied both ActionA and actionB and they both work excellent so this require seems to make sense
  }

}
