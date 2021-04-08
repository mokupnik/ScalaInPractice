package myTraits

package object Actions {

  val actionA: Pluginable = new Shortening with Doubling with SimpleSpacing
  val actionB: Pluginable = new Doubling with Shortening with NoSpacing
  val actionC: Pluginable = new Doubling with LowerCasing
  val actionD: Pluginable = new Rotating with DuplicateRemoval
  val actionE: Pluginable = new Reverting with Doubling with Shortening with NoSpacing
  val actionF: Pluginable = new Rotating {
    override def plugin(text: String): String =
      super.plugin(super.plugin(super.plugin(super.plugin(super.plugin(text)))))
  }

  val actionG: Pluginable = new Pluginable { override def plugin(text: String) = actionB.plugin(actionA.plugin(text)) }

}
