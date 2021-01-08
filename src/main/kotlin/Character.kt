

data class Position(val x: Int, val y: Int)

open class Character(val name: String, val position: Position, val health: Int, val inventory: Inventory)


fun Character.addItem(item: Item) = Character(name, position, health, inventory.addItem(item))

fun Character.showItems(){
    println("------------ $name's Inventory ------------")
    inventory.showItems()
}




class Enemy (name: String, position: Position, health: Int, inventory: Inventory)
    : Character(name, position, health, inventory){

}

fun Enemy.addItem(item: Item) : Enemy = Enemy(name, position, health, inventory.addItem(item))