import pt.isel.canvas.*


data class GUI(val rects: List<Rect>, val windowPosition: Position, val windowWidth: Int, val windowHeight: Int)

fun GUI.slots() = rects.filterIsInstance<Slot>()

fun GUI.selectSlot(slot: Slot) = copy(rects = rects - slot + slot.select())

fun GUI.checkClicks(me: MouseEvent, multiSelect: Boolean): GUI{
    var gui = this
    val clickedSlots = gui.slots().filter { it.isClicked(me) }
    val notClickedSlots = gui.slots() - clickedSlots

    clickedSlots.forEach { slot ->
        //Select or unselect the current slot
        gui = gui.selectSlot(slot)
    }
    //If not multiselecting, verify if other slots are selected and unselect them (change color)
    if(!multiSelect) notClickedSlots.forEach { not_clickedSlot ->
        if(not_clickedSlot.color == CYAN)
            gui = gui.selectSlot(not_clickedSlot)
    }
    return gui
}

fun GUI.addButton(button: Rect) = copy(rects = rects + button)

fun Canvas.drawGUI(gui: GUI){
    gui.rects.forEach { drawRectShape(it) }
    gui.rects.forEach { drawRectText(it) }
}