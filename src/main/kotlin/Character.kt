

data class Position(val x: Int, val y: Int)

open class Character(val name: String, val position: Position, val health: Int, val inventory: Inventory){

}

class Player (name: String, position: Position, health: Int, inventory: Inventory)
    : Character(name, position, health, inventory){

}

fun Character.addItem(item: Item) : Character{
    return Character(name, position, health, inventory.addItem(item))
}

fun Player.addItem(item: Item) : Player = Player(name, position, health, inventory.addItem(item))

fun Character.showItems() = inventory.showItems()