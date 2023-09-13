package ru.smak.polynomial106.math

import kotlin.math.abs

class Polynomial(coeffs: Map<Int, Double>) {
    private val _coeffs: MutableMap<Int, Double>
    val coeffs: Map<Int, Double>
        get() = _coeffs.toMap()

    init{
        _coeffs = coeffs.filter { (k,v) -> v != 0.0 }.toSortedMap()
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

    override fun toString() = toString("x")
 //       return _coeffs.toList().joinToString { "${it.first}: ${it.second}" }


    fun toString(variable: String) = _coeffs.toList().reversed().map{ (k, v) ->
        StringBuilder().apply {
            append(if (v >= 0.0) if (k != _coeffs.keys.max()) "+" else "" else "-")
            if(abs(v) != 1.0 || k == 0) append(abs(v))
            if(k != 0) append(variable)
            if(k > 1) append("^$k")
        }.toString()

    }.joinToString("")

}