import pt.isel.canvas.*

data class Item(val name: String, val value: Int)

data class Inventory(val items: List<Item>, val gui: GUI)

fun Inventory.addItem(item: Item) = copy(items = items + item).updateInventoryGUI()

fun Inventory.removeItem(name: String) : Inventory{
    for(item in items){
        if(item.name == name) return copy(items = items - item).updateInventoryGUI()
    }
    println("There's no item with the name \"$name\" in the inventory!")
    return this
}

fun Inventory.removeItem(id: Int) : Inventory{
    try{
        val item = items[id]
        return copy(items = items - item).updateInventoryGUI()
    }
    catch (e: Exception){ println("There's no item with the id $id in the inventory!") }
    return this
}

fun Inventory.showItems(){
    for(item in items){
        item.apply { println("Name: $name - Value: $value") }
    }
}

fun Inventory.selectSlot(slot: Slot) = copy(gui = gui.selectSlot(slot))

fun Inventory.checkClicks(me: MouseEvent, multiSelect: Boolean) = copy(gui = gui.checkClicks(me, multiSelect))

fun Inventory.updateInventoryGUI() : Inventory{
    val notSlots = gui.rects - gui.rects.filterIsInstance<Slot>().filter { it.clickable }

    var slots = emptyList<Rect>()

    repeat(items.size){
        slots = slots +
                Slot(Position(gui.windowPosition.x, gui.windowPosition.y + TILE_SIDE * (3 + it)),
                    gui.windowWidth, TILE_SIDE, INVENTORY_WINDOW_COLOR,
                    Text(Position(gui.windowPosition.x, gui.windowPosition.y + TILE_SIDE * (4 + it)),
                        items[it].name, 20), true)
    }

    return copy(gui = gui.copy(rects = notSlots + slots))
}

const val INVENTORY_WINDOW_COLOR = 0xabb5c4
const val INVENTORY_LABEL_BACKGROUND_COLOR = 0xc9bb99

fun loadPlayerInventoryGUI(playerName: String): GUI{
    val windowPosition = Position((TRUE_WIDTH * 0.1).toInt(), TILE_SIDE)
    val windowWidth = (TRUE_WIDTH * 0.8).toInt()
    val windowHeight = (TRUE_WIDTH * 0.9).toInt()

    val mainWindow = Rect(windowPosition, windowWidth, windowHeight, INVENTORY_WINDOW_COLOR, text = null)

    val title = "${playerName}'s Inventory"
    val titleRect = Rect(windowPosition, windowWidth, TILE_SIDE + 5, WHITE,
        Text(Position(windowPosition.x, windowPosition.y + TILE_SIDE), title, 20))

    val labelRect = Rect(Position(windowPosition.x, windowPosition.y + TILE_SIDE + 5), windowWidth, TILE_SIDE * 2, INVENTORY_LABEL_BACKGROUND_COLOR,
        Text(Position(windowPosition.x, windowPosition.y + (TILE_SIDE * 2.5).toInt()), "Name", 25))

    return GUI(rects = listOf(mainWindow, titleRect, labelRect), windowPosition, windowWidth, windowHeight)
}