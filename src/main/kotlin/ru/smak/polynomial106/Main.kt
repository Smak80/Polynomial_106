package ru.smak.polynomial106

import ru.smak.polynomial106.math.Polynomial
import ru.smak.polynomial106.math.eq

var t: Polynomial? = null
fun main() {
    val p1 = Polynomial(mapOf(1 to -1.0, 2 to -3.0, 5 to 1.0))
    val p2 = Polynomial(1.0, 1.0, 3.0, -4.0)
    println(p1)
    println(p2)
    println(p1*0.0)
    println(p1 + p2)
    println(p1*p2)

    val p7 = Polynomial(4.0, 1.0, 1.0)
    println(p7(2.0))

    println(0.6+0.1)
    println(0.8-0.1)
    println(0.6+0.1 == 0.8-0.1)
    println((6e-20+2e-20) eq (8e-20-1e-20))
    println(Math.ulp(0.0))
}