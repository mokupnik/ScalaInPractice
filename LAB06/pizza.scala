package subclasses
package pizza

case class Pizza(
                  Type:PizzaT,
                  size:Size,
                  crust:Crust,
                  extraMeat:Meat = NoExtraMeat(), //optional meat
                  extraTopping:ToppingT = NoTopping() //optional topping
                )
{
  override def toString():String = {
    var params = List(Type, extraMeat, extraTopping)
    var str =""
    str+= size.name
    str+= " " + crust.name + " "
    for(x <- params)
    {
      if(x==extraMeat)
      {
        str += "with " + extraMeat.name
      }
      if(x==extraTopping)
      {
        str += " and with " + extraTopping.name
      }
      else if(x!= extraMeat && x!= extraTopping)
      {
        str += x.name + " "
      }
    }

    return str
  }

  val price: Double = List(Type, extraTopping, extraMeat).foldLeft(0.0)((acc,x) => x.price+acc)*size.percent


}