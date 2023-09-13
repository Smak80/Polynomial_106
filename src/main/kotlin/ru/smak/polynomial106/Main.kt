package ru.smak.polynomial106

import ru.smak.polynomial106.math.Polynomial

var t: Polynomial? = null
fun main() {
    val p1 = Polynomial(mapOf(1 to -1.0, 2 to -3.0, 5 to 1.0))
    val p2 = Polynomial(1.0, 1.0, 3.0, -4.0)
    println(p1)
    println(p2)
    println(p1*0.0)
    println(p1 + p2)
    println(p1*p2)
}