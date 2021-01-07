

data class Position(val x: Int, val y: Int)

open class Character(val name: String, val position: Position, val health: Int){
    init {
        println("Created character named $name in the (${position.x},${position.y}) position and with $health health.")
    }
}

class Player (name: String, position: Position, health: Int) : Character(name, position, health){
    init {
        println("Created player named $name in the (${position.x},${position.y}) position and with $health health.")
    }
}