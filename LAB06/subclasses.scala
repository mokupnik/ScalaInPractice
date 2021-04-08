package subclasses
trait Name {val name:String; val price:Double}

abstract class PizzaT(val price:Double,val name:String) extends Name
case class Margarita() extends PizzaT(price = 5.0,name = "margarita")
case class Funghi() extends PizzaT(price = 7, name = "funghi")
case class Peperoni() extends PizzaT(price = 6.5, name = "peperoni")

abstract class ToppingT(val price:Double, val name:String)extends Name
case class NoTopping() extends ToppingT(0, name = "no toppings")
case class Ketchup() extends ToppingT(price = 0.5, name = "ketchup")
case class Garlic() extends ToppingT(price = 0.5, name = "garlic")

abstract class Crust(val name:String)
case class Thin() extends Crust(name = "thin")
case class Thick() extends Crust(name = "thick")


abstract class Drinks(val price:Double, val name:String) extends Name
case class Lemonade() extends  Drinks(2.0, "Lemonade")

abstract class Size(val percent:Double, val name:String)
case class Small() extends Size(percent = 0.9,"Small")
case class Regular() extends Size(1.0,  "Regular" )
case class Large() extends Size(1.5, "Large")


abstract class Meat(val price:Double, val name:String) extends Name
case class NoExtraMeat() extends Meat(0, "no extra meat")
case class Salami() extends Meat(1.0, "extra salami")



abstract class Discount(val percent:Double, val name:String)
case class NoDiscount() extends Discount(1.0, "no discount")
case class Student() extends Discount(0.95, "student")
case class Senior() extends Discount(0.93, "senior")

case class PhoneNumber(number:String) {require(number.matches("^(?=(?:[1-9]){1})(?=[0-9]{8}).*"))}
// i think that covers it? Phone number cant start with 0, has to have 9 numbers between 0-9 each (except for first one)