import pt.isel.canvas.*

data class Position(val x: Int, val y: Int)

open class NPC(val name: String, val position: Position, val health: Int, val inventory: Inventory){

}

class Enemy (name: String, position: Position, health: Int, inventory: Inventory)
    :NPC(name, position, health, inventory)

fun Enemy.copy(name: String = this.name, position: Position = this.position, health: Int = this.health, inventory: Inventory = this.inventory)
    = Enemy(name, position, health, inventory)

fun Enemy.addItem(item: Item) : Enemy = copy(inventory = inventory.addItem(item))

fun Canvas.drawNPC(npc: NPC){
    drawRect(npc.position.x * TILE_SIDE + TILE_SIDE/4, npc.position.y * TILE_SIDE + + TILE_SIDE/4, TILE_SIDE/2, TILE_SIDE/2, BLACK)
}