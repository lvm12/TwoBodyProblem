package model.items.shapes

import androidx.compose.ui.graphics.Color
import model.items.CartesianVector
import model.items.Item
import kotlin.random.Random

data class CircleItem(
    override var name: String,
    var radius: Double,
    override var mass: Double,
    override var velocity: CartesianVector,
    override var position: CartesianVector,
    override var acceleration: CartesianVector,
    override var force: CartesianVector,
    override var color: Color = Color(Random.nextInt(255), Random.nextInt(255), Random.nextInt(255), Random.nextInt(255))
): Item(
    name = name,
    distance = radius,
    velocity = velocity,
    mass = mass,
    position = position,
    acceleration = acceleration,
    force = force,
    color = color
)