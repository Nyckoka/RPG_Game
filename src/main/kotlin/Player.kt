import pt.isel.canvas.*

data class Player (val name: String, val position: Position, val health: Int, val inventory: Inventory)

fun Canvas.drawPlayer(player: Player){
    player.apply { drawRect(position.x * TILE_SIDE, position.y * TILE_SIDE, TILE_SIDE, TILE_SIDE, RED) }
}


fun Player.move(dir : Char) =
    copy(position = Position(position.x + if(dir == 'd') 1 else if(dir == 'a') -1 else 0,
                             position.y + if(dir == 's') 1 else if(dir == 'w') -1 else 0))

fun Player.addItem(item: Item) = copy(inventory = inventory.addItem(item)).updateInventoryGUI()
fun Player.removeItem(itemName: String) = copy(inventory = inventory.removeItem(itemName)).updateInventoryGUI()
fun Player.removeItem(id: Int) = copy(inventory = inventory.removeItem(id)).updateInventoryGUI()

fun Player.selectInventoryButton(button: Button) = copy(inventory = inventory.selectButton(button))

fun Player.checkInventoryClicks(me: MouseEvent, multiSelect: Boolean) = copy(inventory = inventory.checkClicks(me, multiSelect))
