
package object money
{


  //Q: Is it a good practice to hold it all in a package object?
//Q: When i define my methods in money case class, should I write this.amount or just amount when i want to get amount of this object?
  // both versions work but the latter seems cleaener so i decided to go with that one.
  trait Currency {
    val name: String
  }

  case object EUR extends Currency {
    val name = "EUR"
  }

  case object USD extends Currency {
    val name = "USD"
  }

  case object PLN extends Currency {
    val name = "PLN"
  }

// aliases
val $ = USD
val zl = PLN
val E = EUR
// € is not a valid identifier in scala




  case class CurrencyConverter(conversion: Map[(Currency, Currency), BigDecimal]) {

    def convert(from: Currency, to: Currency): BigDecimal = {
      if (conversion.contains(from, to))
        conversion(from, to)
      else
        1 / conversion(to, from)

    }
  }

  val conversion: Map[(Currency, Currency), BigDecimal] = Map((EUR, PLN) -> 4.46767,
                                                              (PLN, USD) -> 0.271846,
                                                              (USD, EUR) -> 0.823328)

  implicit val currencyConverter = CurrencyConverter(conversion)


  implicit def toMoney(x:Double ) = (c: Currency) => Money(BigDecimal(x),c)
//Q: Is this version better than defining implicit class M(x:Double) with an def apply method that takes currency and makes new Money Object or is it worse?


  case class Money(amount: BigDecimal, currency: Currency)(implicit currencyConverter: CurrencyConverter) {

    def round(new_amount: BigDecimal) = new_amount.setScale(3, BigDecimal.RoundingMode.FLOOR)

    // i hope it is okay to round, i wanted to have clean results but im not sure if its ok when it comes to the economic aspects of this problem


    def +(snd: Money): Money = {
      if (currency == snd.currency)
        Money(round(amount + snd.amount), currency)
      else
        Money(round(amount + (snd.amount * currencyConverter.convert(snd.currency, currency))), currency)
    }

    def -(snd: Money): Money = {
      if (currency == snd.currency)
        Money(round(amount - snd.amount), currency)
      else
        Money(round(amount - (snd.amount * currencyConverter.convert(snd.currency, currency))), currency)
    }

    def *(multi: BigDecimal): Money = Money(amount * multi, currency)

    def as(con: Currency) = Money(round(amount * currencyConverter.convert(currency, con)), con)


    def <(snd: Money): Boolean = {
      if (currency == snd.currency)
        amount < snd.amount
      else {
        amount < (currencyConverter.convert(snd.currency, currency) * snd.amount)
      }
    }

    def >(snd: Money): Boolean = {
      if (currency == snd.currency)
        amount > snd.amount
      else
        amount > (currencyConverter.convert(snd.currency, currency) * snd.amount)
    }

    override def toString = amount.toString() + currency.name


  }


}