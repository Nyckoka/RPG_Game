import pt.isel.canvas.*

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

fun Player.addItem(item: Item) = Player(name, position, health, inventory.addItem(item)).updateInventoryGUI()
fun Player.removeItem(itemName: String) = Player(name, position, health, inventory.removeItem(itemName)).updateInventoryGUI()
fun Player.removeItem(id: Int) = Player(name, position, health, inventory.removeItem(id)).updateInventoryGUI()

fun Player.selectButtoninInventory(button: Button, select: Boolean) : Player{
    return Player(name, position, health, Inventory(inventory.items,
        GUI(inventory.gui.buttons - button + button.changeColor(if(select) CYAN else INVENTORY_WINDOW_COLOR))))
}