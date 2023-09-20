package ru.smak.polynomial106.math

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.pow

class Polynomial(coeffs: Map<Int, Double>) {
    private val _coeffs: MutableMap<Int, Double>
    val coeffs: Map<Int, Double>
        get() = _coeffs.toMap()

    init{
        _coeffs = coeffs.filter { (k,v) -> v neq 0.0 }.toSortedMap()
        if(_coeffs.isEmpty()) _coeffs[0] = 0.0
    }

    constructor(vararg coeffs: Double) : this(coeffs.mapIndexed{ i, v -> i to v }.toMap())

    operator fun plus (other: Polynomial) = Polynomial(_coeffs.toMutableMap().also {
        other._coeffs.forEach { (k, v) -> it[k]= v + (it[k] ?: 0.0) }
    })

    operator fun minus (other: Polynomial) = Polynomial(_coeffs.toMutableMap().also {
        other._coeffs.forEach { (k, v) -> it[k]= v - (it[k] ?: 0.0) }
    })

    operator fun times(k: Double) = Polynomial(List(_coeffs.size){
        _coeffs.keys.elementAt(it) to _coeffs[_coeffs.keys.elementAt(it)]!! * k
    }.toMap())

    operator fun times(other: Polynomial) = Polynomial(mutableMapOf<Int, Double>().also{ c ->
        _coeffs.forEach{ (k1 , v1) ->
            other._coeffs.forEach { (k2, v2) ->
                c[k1+k2] = v1*v2+(c[k1+k2]?:0.0)
            }
        }
    })

    operator fun div(scalar: Double) = Polynomial(_coeffs.map { (k, v) -> if (v eq 0.0) throw ArithmeticException("Division by zero") else k to v / scalar }.toMap())

    operator fun invoke(x: Double) = _coeffs.toList().fold(0.0) {
            result, value ->
        result + x.pow(value.first) * value.second
    }

    override fun toString() = toString("x")

    fun toString(variable: String) = _coeffs.toList().reversed().map{ (k, v) ->
        StringBuilder().apply {
            if (v.neq(0.0, 1e-12)) {
                append(if (v > 0.0 || v.eq(0.0, 1e-12)) if (k != _coeffs.keys.max()) "+" else "" else "-")
                if (abs(v) neq 1.0 || k == 0) append(abs(v))
                if (k != 0) append(variable)
                if (k > 1) append("^$k")
            }
        }.toString()

    }.joinToString("")

}

fun Double.eq(other: Double, eps: Double = 1e-15) =
    abs(this - other) < eps

infix fun Double.eq(other: Double) =
    abs(this - other) < max(Math.ulp(this), Math.ulp(other)) * 2

fun Double.neq(other: Double, eps: Double = 1e-15) = !(eq(other, eps))

infix fun Double.neq(other: Double) = !(this eq other)