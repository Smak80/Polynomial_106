package ru.smak.polynomial106.math.polynomial

class LagrangePoly(points: Map<Double, Double>) : Polynomial() {

    private val _points: MutableMap<Double, Double> = points.toMutableMap()
    val points: Map<Double, Double>
        get() = _points.toMap()

    init{
        createLagrangePoly()
    }

    private fun createLagrangePoly() {
        _coeffs.apply {
            clear()
            putAll(_points.toList().fold(Polynomial()){ r, (x, y) ->
                r + createFundamental(x) * y
            }.coeffs)
        }
    }

    private fun createFundamental(xk: Double):Polynomial{
        val result : Polynomial = Polynomial(1.0)
        _points.forEach{(xi, y) -> }
    }
}