import pt.isel.canvas.*
import java.lang.Exception

class Player (name: String, position: Position, health: Int, inventory: Inventory)
    : Character(name, position, health, inventory){

}

fun Canvas.drawPlayer(player: Player){
    player.apply { drawRect(position.x * TILE_SIDE, position.y * TILE_SIDE, TILE_SIDE, TILE_SIDE, RED) }
}


fun Player.move(dir : Char) : Player{
    return Player(name,
        Position(position.x + if(dir == 'd') 1 else if(dir == 'a') -1 else 0,
                 position.y + if(dir == 's') 1 else if(dir == 'w') -1 else 0),
        health, inventory)
}

fun Player.addItem(item: Item) = Player(name, position, health, inventory.addItem(item))
fun Player.removeItem(itemName: String) = Player(name, position, health, inventory.removeItem(itemName))
fun Player.removeItem(id: Int) = Player(name, position, health, inventory.removeItem(id))

const val INVENTORY_WINDOW_COLOR = 0xabb5c4
const val INVENTORY_LABEL_BACKGROUND_COLOR = 0xc9bb99


fun Canvas.drawInventory(game: Game){
    val windowPosition = Position((width * 0.1).toInt(), TILE_SIDE)
    val windowWidth = (width * 0.8).toInt()
    val windowHeight = (width * 0.9).toInt()

    drawRect(windowPosition.x, windowPosition.y, windowWidth, windowHeight, INVENTORY_WINDOW_COLOR)
    drawRect(windowPosition.x, windowPosition.y, windowWidth, TILE_SIDE, WHITE)

    drawText(windowPosition.x + windowWidth/5, windowPosition.y + TILE_SIDE - 3, "${game.player.name}'s Inventory", BLACK, 20)
    drawRect(windowPosition.x, windowPosition.y * 2, windowWidth, TILE_SIDE, INVENTORY_LABEL_BACKGROUND_COLOR)

    drawText(windowPosition.x, windowPosition.y + TILE_SIDE * 2, "Name", BLACK, 20)
    repeat(game.player.inventory.items.size){
        drawText(windowPosition.x, windowPosition.y + TILE_SIDE * (3 + it), "${game.player.inventory.items[it].name} ", BLACK, 20)
    }
}