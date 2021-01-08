

data class Position(val x: Int, val y: Int)


data class Enemy (val name: String, val position: Position, val health: Int, val inventory: Inventory)

fun Enemy.addItem(item: Item) : Enemy = copy(inventory = inventory.addItem(item))