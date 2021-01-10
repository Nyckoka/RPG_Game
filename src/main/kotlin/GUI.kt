import pt.isel.canvas.*


data class GUI(val slots: List<Slot>)

fun GUI.selectSlot(slot: Slot) = copy(slots = slots - slot + slot.select())
fun GUI.unselectSlot(slot: Slot) = copy(slots = slots - slot + slot.unselect())

fun GUI.checkClicks(me: MouseEvent, multiSelect: Boolean): GUI{
    var gui = this
    val clickedSlots = gui.slots.filter { it.isClicked(me) }
    val notClickedSlots = gui.slots - clickedSlots

    clickedSlots.forEach { slot ->
        //Select or unselect the current slot
        gui = if(!slot.selected) gui.selectSlot(slot) else gui.unselectSlot(slot)
    }
    //If not multiselecting, verify if other slots are selected and unselect them (change color)
    if(!multiSelect) notClickedSlots.forEach { notClickedSlot ->
        if(notClickedSlot.selected) {
            gui = gui.unselectSlot(notClickedSlot)
        }
    }
    return gui
}

fun GUI.addSlot(slot: Slot) = copy(slots = slots + slot)