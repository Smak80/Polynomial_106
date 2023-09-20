package ru.smak.polynomial106.math.polynomial

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.pow

open class Polynomial(coeffs: Map<Int, Double>) {
    protected val _coeffs: MutableMap<Int, Double>
    val coeffs: Map<Int, Double>
        get() = _coeffs.toMap()

    init{
        _coeffs = coeffs.filter { (k,v) -> v neq 0.0 }.toMutableMap()
        if(_coeffs.isEmpty()) _coeffs[0] = 0.0
    }

    constructor(vararg coeffs: Double) : this(coeffs.mapIndexed{ i, v -> i to v }.toMap())

    operator fun plus (other: Polynomial) = Polynomial(_coeffs.toMutableMap().also {
        other._coeffs.forEach { (k, v) -> it[k]= v + (it[k] ?: 0.0) }
    })

    operator fun plusAssign(other: Polynomial) {
        other._coeffs.forEach { (k, v) -> _coeffs[k] = v + (_coeffs[k] ?: 0.0)}
    }

    operator fun minus (other: Polynomial) = Polynomial(_coeffs.toMutableMap().also {
        other._coeffs.forEach { (k, v) -> it[k]= (it[k] ?: 0.0) - v }
    })

    operator fun minusAssign(other: Polynomial) {
        other._coeffs.forEach { (k, v) -> _coeffs[k] = (_coeffs[k] ?: 0.0) - v}
    }

    operator fun times(k: Double) = Polynomial(List(_coeffs.size){
        _coeffs.keys.elementAt(it) to _coeffs[_coeffs.keys.elementAt(it)]!! * k
    }.toMap())

    operator fun timesAssign(k: Double){
        _coeffs.keys.forEach { _coeffs[it] = _coeffs[it]!! * k }
    }

    operator fun times(other: Polynomial) = Polynomial(mutableMapOf<Int, Double>().also{ c ->
        _coeffs.forEach{ (k1 , v1) ->
            other._coeffs.forEach { (k2, v2) ->
                c[k1+k2] = v1*v2+(c[k1+k2]?:0.0)
            }
        }
    })
    operator fun timesAssign(other: Polynomial) {
        _coeffs.apply {
            putAll((this@Polynomial * other)._coeffs.also { _coeffs.clear() })
        }
    }


    operator fun div(scalar: Double) = Polynomial(_coeffs.map { (k, v) -> if (v eq 0.0) throw ArithmeticException("Division by zero") else k to v / scalar }.toMap())

    operator fun invoke(x: Double) = _coeffs.entries.sumOf { (degree, value) -> x.pow(degree) * value }

    override fun toString() = toString("x")

    fun toString(variable: String) = _coeffs.toSortedMap(reverseOrder()).map{ (k, v) ->
        buildString {
            if (v.neq(0.0, 1e-12)) {
                append(if (v > 0.0 || v.eq(0.0, 1e-12)) if (k != _coeffs.keys.max()) "+" else "" else "-")
                if (abs(v) neq 1.0 || k == 0) append(abs(v))
                if (k != 0) append(variable)
                if (k > 1) append("^$k")
            }
        }
    }.joinToString("")

}

fun Double.eq(other: Double, eps: Double = 1e-15) =
    abs(this - other) < eps

infix fun Double.eq(other: Double) =
    abs(this - other) < max(Math.ulp(this), Math.ulp(other)) * 2

fun Double.neq(other: Double, eps: Double = 1e-15) = !(eq(other, eps))

infix fun Double.neq(other: Double) = !(this eq other)