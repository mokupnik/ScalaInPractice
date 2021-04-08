package myTraits


object Actions {

// i hope order: NoSpacing => Shortening => Doubling => Reverting means that we should do NoSpacing first

  val actionA:Pluginable =  new Shortening with Doubling with SimpleSpacing
  val actionB:Pluginable = new Doubling with Shortening with NoSpacing
  val actionC:Pluginable = new Doubling with LowerCasing
  val actionD:Pluginable = new Rotating with DuplicateRemoval
  val actionE:Pluginable = new Reverting with Doubling with Shortening with NoSpacing
  val actionF:Pluginable = new Rotating  {override def plugin(text: String): String = return super.plugin(super.plugin(super.plugin(super.plugin(super.plugin(text)))))}
  val actionG:Pluginable = new Pluginable { override def plugin(text:String ) = actionB.plugin(actionA.plugin(text))}

  def main(args: Array[String]) {


 val text = "a  B  c"
    require(actionA.plugin(text)== "a  c") // "a b c" => "a  b  c" => "a  c"
    require(actionB.plugin(text)== "acc") // "abc" => "ac" => "acc"
    require(actionC.plugin(text)=="a   bb   c") // "a  b  c" =>>"a   bb   c"
    require(actionD.plugin(text)=="caB") // "aBc" => "caB"
    require(actionE.plugin(text)=="cca")// "aBc" => "ac" => "acc" => "caa"
    require(actionF.plugin(text)==" B  ca ") // after first rotation  "ca  B  "=>next two rotations we put two whitespaces in the front, next we push B in the front, then another whitespace
    require(actionG.plugin(text)==actionB.plugin(actionA.plugin(text))) // i studied both ActionA and actionB and they both work excellent so this require seems to make sense



  }

}
