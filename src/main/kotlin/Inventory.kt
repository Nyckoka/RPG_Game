import pt.isel.canvas.*

data class Item(val name: String, val value: Int)

data class Inventory(val items: List<Item>)

fun Inventory.addItem(item: Item) = copy(items = items + item)

fun Inventory.removeItem(name: String) : Inventory{
    for(item in items){
        if(item.name == name) return copy(items = items - item)
    }
    println("There's no item with the name \"$name\" in the inventory!")
    return this
}

fun Inventory.removeItem(id: Int) : Inventory{
    try{
        val item = items[id]
        return copy(items = items - item)
    }
    catch (e: Exception){ println("There's no item with the id $id in the inventory!") }
    return this
}

fun Inventory.showItems(){
    for(item in items){
        item.apply { println("Name: $name - Value: $value") }
    }
}


object InventoryGUI{
    const val WINDOW_COLOR = 0xfce89d  //BEIGE
    const val WINDOW_BORDER_COLOR = 0xccbc7e
    const val BORDER_THICKNESS = 5

    val windowPosition = Position((TRUE_WIDTH * 0.1).toInt(), TILE_SIDE * 2)
    const val windowWidth = (TRUE_WIDTH * 0.8).toInt()
    const val windowHeight = (TRUE_HEIGHT * 0.8).toInt()

    val slot_xRange = windowPosition.x..windowPosition.x + windowWidth

    object Slot{
        val NAME_X = windowPosition.x + BORDER_THICKNESS
        const val WIDTH = windowWidth
        const val HEIGHT = TILE_SIDE
        val LABEL_DISTANCE = WIDTH / 2
        val LABEL_Y = windowPosition.y + TILE_SIDE * 2 + 10
        val LABEL_TO_ITEMS_DISTANCE = 10
        val ITEM_SLOTS_START_Y = LABEL_Y + LABEL_TO_ITEMS_DISTANCE
    }

    object CloseButton{
        val X = windowPosition.x + windowWidth - TILE_SIDE - BORDER_THICKNESS / 2
        val Y = windowPosition.y + BORDER_THICKNESS / 2
        const val WIDTH = TILE_SIDE
        const val HEIGHT = TILE_SIDE
        private const val FIX_CONSTANT = 3
        val LINE_START_X = X + FIX_CONSTANT
        val LINE_START_Y = Y + FIX_CONSTANT
        val LINE_FINISH_X = X + TILE_SIDE - FIX_CONSTANT
        val LINE_FINISH_Y = Y + TILE_SIDE - FIX_CONSTANT
    }
}


fun Canvas.drawPlayerInventory(game: Game){ InventoryGUI.apply {
    val gui = game.gui
    val inventory = game.player.inventory

    //Main window
    drawRect(windowPosition.x, windowPosition.y, windowWidth, windowHeight, WINDOW_COLOR)

    //Label lines
    drawLine(windowPosition.x, windowPosition.y + TILE_SIDE + 5, windowPosition.x + windowWidth, windowPosition.y + TILE_SIDE + 5, BLACK)
    drawLine(windowPosition.x, windowPosition.y + TILE_SIDE * 2 + 15, windowPosition.x + windowWidth, windowPosition.y + TILE_SIDE * 2 + 15, BLACK)

    //drawRect(windowPosition.x, windowPosition.y + TILE_SIDE + 5, windowWidth, (TILE_SIDE * 1.5).toInt(), BLACK, 4)

    //Title text
    drawText(windowPosition.x + BORDER_THICKNESS, windowPosition.y + TILE_SIDE, "$PLAYER_NAME's Inventory", BLACK, 20)

    InventoryGUI.Slot.apply{
        //Label "Name" text
        drawText(NAME_X, LABEL_Y, "Name", BLACK, 25)
        //Label "Value" text
        drawText(NAME_X + LABEL_DISTANCE, LABEL_Y, "Value", BLACK, 25)

        //Slot rectangles
        for(slot in gui.slots){
            drawRect(windowPosition.x, slot.yPos(), WIDTH, HEIGHT,
                color = if(slot.selected) CYAN else WINDOW_COLOR)
        }
        //Item names
        for(slot in gui.slots){
            drawText(NAME_X, slot.yPos() + HEIGHT, inventory.items[slot.position].name, BLACK, 20)
        }
        //Item values
        for(slot in gui.slots){
            drawText(NAME_X + LABEL_DISTANCE, slot.yPos() + HEIGHT,
                (inventory.items[slot.position].value).toString(), BLACK, 20)
        }
    }

    //Close button
    InventoryGUI.CloseButton.apply {
        drawRect(X, Y, WIDTH, HEIGHT, RED)
        drawLine(LINE_START_X, LINE_START_Y, LINE_FINISH_X, LINE_FINISH_Y, WHITE)
        drawLine(LINE_START_X, LINE_FINISH_Y, LINE_FINISH_X, LINE_START_Y, WHITE)
    }

    //Main window border
    drawRect(windowPosition.x, windowPosition.y, windowWidth, windowHeight, WINDOW_BORDER_COLOR, BORDER_THICKNESS)
}}

fun Game.updateInventoryItemSlots() : Game{
    var slots = emptyList<Slot>()

    repeat(player.inventory.items.size){
        val selected = try{ gui.slots[it].selected } catch (e: Exception){ false }
        slots = slots + Slot(it, selected)
    }

    return copy(gui = gui.copy(slots = slots))
}