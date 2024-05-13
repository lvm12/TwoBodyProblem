package view

import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import model.World
import model.items.CartesianVector
import model.items.Item
import model.items.shapes.CircleItem
import util.Log
import kotlin.random.Random

@Composable
fun Simulation(
    start:()-> Unit,
    stop:()-> Unit,
    addItem:(item: Item)-> Unit,
    removeItem:(item: Item)-> Unit,
    world: World
) {
    val TAG = "Simulation"
    LaunchedEffect(world.started){
        Log(TAG, "World State: ${world.started}")
    }
    val stateList = arrayOfNulls<State<Offset>>(world.items.size)
    world.items.forEachIndexed { index, obj ->
        stateList[index] =
            animateOffsetAsState(
                targetValue = Offset(
                    x = obj.position.y.toFloat()* 1000 ,
                    y = obj.position.x.toFloat()* 1000
                ),
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 1000
                    },
                )
            )
        Log(TAG, "offset: ${stateList[index]!!.value}")
    }
    Column(verticalArrangement = Arrangement.Bottom) {
        Row {
            Button(
                onClick = {
                    start()
                }
            ){
                Text("Start")
            }
            Button(
                onClick = {
                    stop()
                }
            ){
                Text("Stop")
            }
            Button(
                onClick = {
                    addItem(
                        CircleItem(
                        name = "Mass 3",
                        radius = 50.0,
                        mass = 1.0,
                        position = CartesianVector(0.6,0.96),
                        velocity = CartesianVector(0.0,0.0),
                        acceleration = CartesianVector(0.0,0.0),
                        force = CartesianVector(0.0,0.0),
                        color = Color.Blue
                    )
                    )
                }
            ){
                Text("Add")
            }
            Button(
                onClick = {
                    removeItem(
                        CircleItem(
                            name = "Mass 3",
                            radius = 50.0,
                            mass = 1.0,
                            position = CartesianVector(0.6,0.96),
                            velocity = CartesianVector(0.0,0.0),
                            acceleration = CartesianVector(0.0,0.0),
                            force = CartesianVector(0.0,0.0),
                            color = Color.Blue
                        )
                    )
                }
            ){
                Text("Remove")
            }
        }
        Canvas(modifier = Modifier.fillMaxSize()) {
            world.items.forEachIndexed { index, item ->
                drawCircle(
                    color = item.color,
                    radius = item.distance.toFloat(),
                    center = if (stateList[index] != null) stateList[index]!!.value
                    else Offset.Zero
                )
            }
        }
    }
}