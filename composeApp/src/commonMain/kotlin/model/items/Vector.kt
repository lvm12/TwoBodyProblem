package model.items

import kotlin.math.*

data class CartesianVector(
    val x: Double,
    val y: Double,
){
    fun toPolarVector(): PolarVector {
        val magnitude = sqrt(x.pow(2) + y.pow(2))
        val angle = atan2(y, x)
        return PolarVector(
            magnitude = magnitude,
            direction = angle
        )
    }

    override fun toString(): String {
        return "($x, $y)"
    }
    operator fun div(n: CartesianVector): CartesianVector{
        return CartesianVector(
            x = x / n.x,
            y = y / n.y
        )
    }
    operator fun div(n: Double): CartesianVector{
        return CartesianVector(
            x = x/n,
            y = y/n
        )
    }
    operator fun times(n: CartesianVector): CartesianVector{
        return CartesianVector(
            x = x * n.x,
            y = y * n.y
        )
    }
    operator fun times(n: Double): CartesianVector{
        return CartesianVector(
            x = x * n,
            y = y * n
        )
    }
    operator fun plus(n: CartesianVector): CartesianVector{
        return CartesianVector(
            x = x + n.x,
            y = y + n.y
        )
    }
    operator fun minus(n: CartesianVector): CartesianVector{
        return CartesianVector(
            x = x - n.x,
            y = y - n.y
        )
    }
    fun magnitude(): Double{
        return sqrt(x.pow(2) + y.pow(2))
    }
    fun magnitudeSquared(): Double{
        return x.pow(2) + y.pow(2)
    }
    fun norm(): CartesianVector{
        val magnitude = magnitude()
        return CartesianVector(
            x = x/magnitude,
            y = y/magnitude
        )
    }
}

fun List<CartesianVector>.sumOf(): CartesianVector{
    return CartesianVector(
        x = sumOf { it.x },
        y = sumOf { it.y }
    )
}

data class PolarVector(
    val magnitude: Double,
    val direction: Double,
){
    fun toCartesianVector(): CartesianVector {
        return CartesianVector(
            x = magnitude*cos(direction),
            y = magnitude*sin(direction),
        )
    }
}