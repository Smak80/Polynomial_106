package ru.smak.polynomial106

import ru.smak.polynomial106.math.Polynomial

var t: Polynomial? = null
fun main() {
    val p1 = Polynomial(mapOf(1 to 5.0, 2 to -3.0, 5 to 1.0))
    val p2 = Polynomial(0.0, 0.0, 7.0, -4.0)
    println(p1)
    println(p2)
    println(p1.times(0.0))
    println(p1.plus(p2))
}