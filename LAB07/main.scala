package money

object tests {


  def main(args: Array[String]): Unit = {

    val sum1: Money = 100.01(USD) + 200(EUR) //result in dollars

    val sum2: Money = 100.01(zl) + 200($) //result in złoty

    val sum3: Money = 5(zl) + 3(PLN) + 20.5(USD) //result in złoty


    val sub: Money = 300.01 (USD) - 200 (EUR)

    val mult1: Money = 30 (zl) * 20 //result in złoty
    val mult2: Money = 20 ($) * 11 //result in dollars



    val conv1: Money = 150.01 (USD) as PLN // converts to złoty
    val conv2: Money = 120.01 (USD) as E // converts to euro


    val compare1: Boolean = 300.30 (USD) > 200 (E)
    val compare2: Boolean = 300.30 ($) < 200 (EUR)

    print("100.01(USD) + 200(EUR) = ")
    println(sum1)
    print("100.01(zl) + 200($) = ")
    println(sum2)
    print("5(zl) + 3(PLN) + 20.5(USD) = ")
    println(sum3)
    print("300.01 (USD) - 200 (EUR) = ")
    println(sub)
    print("30 (zl) * 20")
    println(mult1)
    print("20 ($) * 11 = ")
    println(mult2)
    print("150.01 (USD) converted to PLN = ")
    println(conv1)
    print("120.01 (USD) converted to EUR = ")
    println(conv2)
    print("300.30 (USD) > 200 (EUR) is ")
    println(compare1)
    print("300.30 ($) < 200 (EUR) is ")
    println(compare2)

  }
}
