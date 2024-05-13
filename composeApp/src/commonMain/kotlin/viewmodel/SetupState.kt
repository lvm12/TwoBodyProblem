package viewmodel

import model.items.Item

data class SetupState(
    val items: List<Item> = mutableListOf(),
    val started: Boolean = false,
)
