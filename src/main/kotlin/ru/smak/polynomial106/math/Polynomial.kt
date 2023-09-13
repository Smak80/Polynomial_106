package ru.smak.polynomial106.math

class Polynomial(coeffs: Map<Int, Double>) {
    private val _coeffs: MutableMap<Int, Double>
    val coeffs: Map<Int, Double>
        get() = _coeffs.toMap()

    init{
        _coeffs = coeffs.toMutableMap()
    }

    constructor(vararg coeffs: Double) : this(HashMap<Int, Double>().apply {
        coeffs.forEachIndexed{
            i,v -> if (v!=0.0) put(i, v)
        }
    })

    fun plus (other: Polynomial) = Polynomial(_coeffs.toMutableMap().also {
        other._coeffs.forEach { (k, v) -> it[k]= v + (it[k] ?: 0.0) }
    })

    fun times(k: Double) = Polynomial(List(_coeffs.size){
        _coeffs.keys.elementAt(it) to _coeffs[_coeffs.keys.elementAt(it)]!! * k
    }.toMap())


    override fun toString(): String {
        return _coeffs.toList().joinToString { "${it.first}: ${it.second}" }
    }

}