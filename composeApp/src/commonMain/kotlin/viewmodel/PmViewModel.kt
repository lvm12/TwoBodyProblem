package viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import model.World
import model.items.CartesianVector
import model.items.Item
import model.items.shapes.CircleItem

class PmViewModel: ViewModel() {
    private val state = MutableStateFlow(SetupState(
        items = listOf<Item>(
            //            CircleItem(
//                name = "Earth",
//                radius = 6.3781e+6,
//                mass = 5.9722e+24,
//                position = CartesianVector(0.0,0.0),
//                velocity = CartesianVector(0.0,0.0),
//                force = CartesianVector(0.0,0.0),
//                acceleration = CartesianVector(0.0,0.0),
//            ),
//            CircleItem(
//                name = "Mars",
//                radius = 3.3962e+6,
//                mass = 6.42e+23,
//                position =CartesianVector(
//                    2.25e+11,
//                    0.0
//                ),
//                velocity = CartesianVector(0.0,0.0),
//                acceleration = CartesianVector(0.0,0.0),
//                force = CartesianVector(0.0,0.0)
//            )
            CircleItem(
                name = "Mass 1",
                radius = 50.0,
                mass = 1.0,
                position = CartesianVector(0.1,0.1),
                velocity = CartesianVector(0.0,0.0),
                acceleration = CartesianVector(0.0,0.0),
                force = CartesianVector(0.0,0.0),
                color = Color.Green
            ),
            CircleItem(
                name = "Mass 2",
                radius = 50.0,
                mass = 1.0,
                position = CartesianVector(1.1,0.1),
                velocity = CartesianVector(0.0,0.0),
                acceleration = CartesianVector(0.0,0.0),
                force = CartesianVector(0.0,0.0),
                color = Color.Red
            ),

        )
    ))
    val world = state.map {
        World(
            items = it.items,
            started = it.started,
            deltaT = 1000.0
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        initialValue = World(
            started = SetupState().started,
            items = SetupState().items
        )
    )
    var selectedItem: Item? = null
        private set

    fun addItem(item: Item) {
        state.update { it.copy(
            items = run {
                val list = it.items.toMutableList()
                list.add(item)
                list.toList()
            }
        ) }
    }
    fun removeItem(item: Item) {
        state.update { it.copy(
            items = run {
                val list = it.items.toMutableList()
                list.remove(item)
                list.toList()
            }
        )}
    }
    fun start(){
        viewModelScope.launch {
            state.update {
                it.copy(
                    started = true
                )
            }
            world.value.repeatUntil { central, satellite ->
                central.position.x > satellite.position.x
            }
            println("Offset: From ViewModel: WorldState: ${world.value.started}")
        }
    }
    fun stop(){

    }
}