import pt.isel.canvas.*

data class Item(val name: String, val value: Int)

data class Inventory(val items: List<Item>, val gui: GUI)

fun Inventory.addItem(item: Item) = Inventory(items + item, gui)

fun Inventory.removeItem(name: String) : Inventory{
    for(item in items){
        if(item.name == name) return copy(items = items - item)
    }
    println("There's no item with the name \"$name\" in the inventory!")
    return this
}
fun Inventory.removeItem(id: Int) : Inventory{
    try{ return copy(items = items - items[id]) }
    catch (e: Exception){ println("There's no item with the id $id in the inventory!") }
    return this
}

fun Inventory.showItems(){
    for(item in items){
        item.apply { println("Name: $name - Value: $value") }
    }
}


fun Inventory.selectButton(button: Button) = copy(gui = gui.selectButton(button))

fun Inventory.checkClicks(me: MouseEvent, multiSelect: Boolean) = copy(gui = gui.checkClicks(me, multiSelect))

const val INVENTORY_WINDOW_COLOR = 0xabb5c4
const val INVENTORY_LABEL_BACKGROUND_COLOR = 0xc9bb99

fun Player.updateInventoryGUI() : Player{
    val windowPosition = Position((TRUE_WIDTH * 0.1).toInt(), TILE_SIDE)
    val windowWidth = (TRUE_WIDTH * 0.8).toInt()
    val windowHeight = (TRUE_WIDTH * 0.9).toInt()

    var InventoryGUI = emptyList<Button>()
    val mainWindow = Button(windowPosition, windowWidth, windowHeight, INVENTORY_WINDOW_COLOR, text = null, false)

    val title = "${name}'s Inventory"
    val titleRect = Button(windowPosition, windowWidth, TILE_SIDE + 5, WHITE,
        Text(Position(windowPosition.x, windowPosition.y + TILE_SIDE), title, 20), false)

    val labelRect = Button(Position(windowPosition.x, windowPosition.y + TILE_SIDE + 5), windowWidth, TILE_SIDE * 2, INVENTORY_LABEL_BACKGROUND_COLOR,
        Text(Position(windowPosition.x, windowPosition.y + (TILE_SIDE * 2.5).toInt()), "Name", 25), false)

    InventoryGUI = InventoryGUI + mainWindow + titleRect + labelRect

    repeat(inventory.items.size){
        InventoryGUI = InventoryGUI +
                Button(Position(windowPosition.x, windowPosition.y + TILE_SIDE * (3 + it)),
                    windowWidth, TILE_SIDE, INVENTORY_WINDOW_COLOR,
                    Text(Position(windowPosition.x, windowPosition.y + TILE_SIDE * (4 + it)),
                        inventory.items[it].name, 20), true)
    }

    return copy(inventory = Inventory(inventory.items, GUI(InventoryGUI)))
}