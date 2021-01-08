import pt.isel.canvas.*

class Item(val name: String, val value: Int){

}

class Inventory(val items: List<Item>, val gui: GUI) {

}

fun Inventory.addItem(item: Item) = Inventory(items + item, gui)

fun Inventory.removeItem(name: String) : Inventory{
    for(item in items){
        if(item.name == name) return Inventory(items - item, gui)
    }
    println("There's no item with the name \"$name\" in the inventory!")
    return this
}
fun Inventory.removeItem(id: Int) : Inventory{
    try{ return Inventory(items - items[id], gui) }
    catch (e: Exception){ println("There's no item with the id $id in the inventory!") }
    return this
}

fun Inventory.showItems(){
    for(item in items){
        item.apply { println("Name: $name - Value: $value") }
    }
}


const val INVENTORY_WINDOW_COLOR = 0xabb5c4
const val INVENTORY_LABEL_BACKGROUND_COLOR = 0xc9bb99


fun Player.updateInventoryGUI() : Player{
    val windowPosition = Position((TRUE_WIDTH * 0.1).toInt(), TILE_SIDE)
    val windowWidth = (TRUE_WIDTH * 0.8).toInt()
    val windowHeight = (TRUE_WIDTH * 0.9).toInt()

    var InventoryGUI = emptyList<Button>()
    val mainWindow = Button(windowPosition, windowWidth, windowHeight, INVENTORY_WINDOW_COLOR, text = null, false)

    val titleRect = Button(windowPosition, windowWidth, TILE_SIDE, WHITE,
        Text(Position(windowPosition.x + windowWidth/5, windowPosition.y + TILE_SIDE),
            "${name}'s Inventory", 20), false)

    val labelRect = Button(Position(windowPosition.x, windowPosition.y + TILE_SIDE), windowWidth, TILE_SIDE * 2, INVENTORY_LABEL_BACKGROUND_COLOR,
        Text(Position(windowPosition.x, windowPosition.y + (TILE_SIDE * 2.5).toInt()), "Name", 25), false)

    InventoryGUI = InventoryGUI + mainWindow + titleRect + labelRect

    repeat(inventory.items.size){
        InventoryGUI = InventoryGUI +
                Button(Position(windowPosition.x, windowPosition.y + TILE_SIDE * (3 + it)),
                    windowWidth, TILE_SIDE, INVENTORY_WINDOW_COLOR,
                    Text(Position(windowPosition.x, windowPosition.y + TILE_SIDE * (4 + it)),
                        inventory.items[it].name, 20), true)
    }

    return Player(name, position, health, Inventory(inventory.items, GUI(InventoryGUI)))

    /*drawRect(windowPosition.x, windowPosition.y, windowWidth, windowHeight, INVENTORY_WINDOW_COLOR)
    drawRect(windowPosition.x, windowPosition.y, windowWidth, TILE_SIDE, WHITE)

    drawText(windowPosition.x + windowWidth/5, windowPosition.y + TILE_SIDE - 3, "${game.player.name}'s Inventory", BLACK, 20)
    drawRect(windowPosition.x, windowPosition.y * 2, windowWidth, TILE_SIDE, INVENTORY_LABEL_BACKGROUND_COLOR)

    drawText(windowPosition.x, windowPosition.y + TILE_SIDE * 2, "Name", BLACK, 20)
    repeat(game.player.inventory.items.size){
        drawText(windowPosition.x, windowPosition.y + TILE_SIDE * (3 + it), "${game.player.inventory.items[it].name} ", BLACK, 20)
    }*/
}