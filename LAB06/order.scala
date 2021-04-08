package subclasses
package pizza
package order

import scala.collection.mutable.Map
class Order
(
  name: String,
  address: String,
  phone:PhoneNumber,//mandatory validated phone-number (hint: regex)
  pizzas:List[Pizza],
  drinks:List[Drinks],
  discount:Discount = NoDiscount(), //optional value
  specialInfo:Option[String] = None //optional text, like: “Ring doesnt work,
)
{

  override def toString():String = {

    //var str = pizzas.tail.foldLeft(pizzas.head.toString())((acc, x) => acc + ", " + x.toString().toLowerCase())
    //^ this was my previous version for pizzas but i figured that if somebody orders one pizza 10 times it becomes
    // really unpleasant to read so i change it with the use of maps
    var str = ""

    var count = Map(pizzas.head.toString() -> 1)
    var pizza_to_string = pizzas.tail.map(x => x.toString())

    for(x <- pizza_to_string)
      {
        {
          if (count.contains(x)) {count(x) += 1}
          else count(x) = 1
        }
      }


    if(drinks.length != 0) {
      var drinks_names = drinks.map(x => x.name)
      for (x <- drinks_names) {
        if (count.contains(x)) {
          count(x) += 1
        }
        else count(x) = 1
      }
    }

    var i = 1
    str += "You ordered: "
    for(x <- count.keys)
    {
      if(i==1) {str += count(x) + " x " + x + ""; i=0}
      else str +=", " + count(x) + " x " + x

    }

    if(specialInfo!=None)
    {
      str += ". Special info: " + specialInfo.get

    }
    str+= "."
    return str

  }

  def extraMeatPrice: Option[Double] = {
    val r = pizzas.foldLeft(0.0)((acc,x) => acc + x.extraMeat.price)
    if(r==0) {return None}
    else return Option(r)

  }

  def pizzasPrice: Option[Double] =
    if (pizzas.length!=0)
    {return Option(pizzas.foldLeft(0.0)((acc,x) => acc + x.price))}
    else return None


  def drinksPrice: Option[Double] = {
    if (drinks.length == 0) {return None}
    else return Option(drinks.foldLeft(0.0)((acc,x) => acc + x.price))
  }



  def priceByType(Type:PizzaT): Option[Double] = {
    if(pizzas.filter(x => x.Type == Type).length == 0)
      {return None}
    else return Option(pizzas.filter(x => x.Type == Type).foldLeft(0.0)((acc,x) => acc + x.price))
  }


  val price: Double = List(pizzasPrice, drinksPrice).filterNot(x => x == None).foldLeft(0.0)((acc,x) => acc + x.get) * discount.percent
  // only when im handling price i worry about the discount, it woudlnt be a problem to add it to other functions,
  // but i think i wasnt supposed to.



}
