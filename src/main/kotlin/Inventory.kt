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


const val INVENTORY_WINDOW_COLOR = 0xfce89d  //BEIGE
const val INVENTORY_WINDOW_BORDER_COLOR = 0xccbc7e
const val INVENTORY_LABEL_BACKGROUND_COLOR = 0xc9bb99
const val INVENTORY_BORDER_THICKNESS = 5

val windowPosition = Position((TRUE_WIDTH * 0.1).toInt(), TILE_SIDE)
const val inventoryWindowWidth = (TRUE_WIDTH * 0.8).toInt()
const val inventoryWindowHeight = (TRUE_WIDTH * 0.9).toInt()

fun Canvas.drawInventory(inventory: Inventory){
    //Main window
    drawRect(windowPosition.x, windowPosition.y, inventoryWindowWidth, inventoryWindowHeight, INVENTORY_WINDOW_COLOR)

    //Label rectangle
    drawRect(windowPosition.x, windowPosition.y + TILE_SIDE, inventoryWindowWidth, TILE_SIDE * 2, INVENTORY_LABEL_BACKGROUND_COLOR)

    //Title text
    drawText(windowPosition.x + INVENTORY_BORDER_THICKNESS, windowPosition.y + TILE_SIDE, "$PLAYER_NAME's Inventory", BLACK, 20)

    //Label text
    drawText(windowPosition.x + INVENTORY_BORDER_THICKNESS, windowPosition.y + TILE_SIDE * 2, "Name", BLACK, 25)

    for(slot in inventory.gui.slots){
        drawRect(windowPosition.x, slot.yPos(), inventoryWindowWidth, TILE_SIDE,
                color = if(slot.selected) CYAN else INVENTORY_WINDOW_COLOR)
    }
    for(slot in inventory.gui.slots){
        drawText(windowPosition.x, slot.yPos() + TILE_SIDE, inventory.items[slot.position].name, BLACK, 20)
    }

    //Main window border
    drawRect(windowPosition.x, windowPosition.y, inventoryWindowWidth, inventoryWindowHeight, INVENTORY_WINDOW_BORDER_COLOR, INVENTORY_BORDER_THICKNESS)

    //Close button
    drawRect(windowPosition.x + inventoryWindowWidth - TILE_SIDE, windowPosition.y,
            TILE_SIDE, TILE_SIDE, RED)
}

fun Inventory.updateInventoryGUI() : Inventory{
    var slots = emptyList<Slot>()
    repeat(items.size){
        slots = slots + Slot(it, false)
    }

    return copy(gui = gui.copy(slots = slots))
}