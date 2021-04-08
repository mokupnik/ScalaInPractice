package subclasses
package pizza
package order


object tests {
  def main(args: Array[String]) {
    val margarita = new Margarita
    val large = new Large
    val ketchup = new Ketchup
    val salami = new Salami
    val thin = new Thin
    val Pizza = new Pizza(margarita,large,thin) // 7.50
    val funghi = new Funghi
    val small = new Small
    val garlic = new Garlic
    val thick = new Thick
    val Lemonade = new Lemonade
    val Lemogrenade = new Lemonade


    val Piza = new Pizza(funghi, small, thick, extraTopping = garlic, extraMeat = salami) // 0.9*8,50 = 7.65
    val Pizzza = new Pizza(margarita,large, thick, extraTopping = ketchup, extraMeat = salami) // 1.5*6.50 = 9.75
    val phone = new PhoneNumber("532341245")
    val senior = new Senior
    //val snd_phone = new PhoneNumber("023543123") causes exception
   // val trd_phone = new PhoneNumber("523123") causes exception
    val specialinfo = Option("Ring doesnt work, please knock loudly")

    val order = new Order("Maciej Okupnik", "Kotowice Rubinowa 7",phone, List(Pizza, Piza),List(Lemogrenade, Lemonade), specialInfo = specialinfo)
    val new_order = new Order("Piotr Nowak", "Warszawa ulica Głowna 18/53",phone, List(Pizza, Piza, Pizzza), List(), discount = senior, Option("Great band"))

    val peperoni = new Peperoni
    println(order)
    require(order.price==19.15) // 7.50+7.65 + 2  + 2 = 19.15
    require(order.extraMeatPrice==Some(1)) // we get extra meat on only one pizza so it should be 1
    require(order.priceByType(margarita)== Some(7.50))
    require(order.drinksPrice==Some(4))
    println(new_order)
    require(new_order.price==23.157) // (7.50 + 7.65 + 9.75)*0.93= 23.157
    require(new_order.pizzasPrice==Some(24.9)) // 7.50 + 7.65 + 9.75 = 24.9
    require(new_order.extraMeatPrice==Some(2)) // we get extra meat on 2 pizzas so 2*1 = 2
    require(new_order.priceByType(funghi)==Some(7.65)) // one small funghi with topping and extra meat so 0.9*(8,50
    require(new_order.drinksPrice == None) // no drinks in this order
     // i also wondered if i should return Some(0.0) or None in some of these functions.
    // I guess it makes more sense to return None
    require(new_order.priceByType(peperoni) == None)
  }
}