package model

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import model.items.CartesianVector
import model.items.Item
import model.items.sumOf
import util.Log

const val G = 6.6743e-11

data class World(
    var started: Boolean = false,
    var items: List<Item> = emptyList(),
    val deltaT: Double = 10.0
){
    val TAG = "World"
    var t = 0.0
    suspend fun next(){
        t += deltaT
        items.forEach {
            Log(TAG, "${it.name} at ${it.position}")
            Log(TAG, "${it.name} acceleration: ${it.acceleration.magnitude()}")
        }
        items.forEach{ item ->
            val forces = items.map {
                val force = calculateForce(central = item, satellite = it)
                //Log(TAG, "force: $force")
                force
            }
            val sumForces = forces.sumOf()
            item.force = sumForces
            item.calculate(deltaT)
        }
        //Log(TAG, "Time passed: $t")
        delay(200L)
    }
    private fun calculateForce(central: Item, satellite: Item): CartesianVector{
        if (central == satellite) return CartesianVector(0.0,0.0)
        val r = satellite.position - central.position
        //Log(TAG, satellite.position)
        //Log(TAG, central.position)
        //Log(TAG, "Distance: $r")
        val fv  = (G * central.mass * satellite.mass)/(r.magnitudeSquared())
        return r.norm()*fv
    }
    suspend fun start(){
        started = true
        while(started){
            next()
        }
    }
    suspend fun repeat(n: Int){
        repeat(n){
            next()
        }
    }
    suspend fun repeatUntil(
        case: (central: Item, satellite: Item) -> Boolean
    ){
        val central = items[0]
        val satellite = items[1]
        while (!case(central, satellite)) {
            next()
        }
    }
}
