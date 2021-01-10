import pt.isel.canvas.*


data class GUI(val slots: List<Slot>)

fun List<Slot>.replace(old: Slot, new: Slot) = toMutableList().apply { this[this.indexOf(old)] = new }

fun GUI.selectSlot(slot: Slot) = copy(slots = slots.replace(slot, slot.select()))

/**
 * Unselects all slots. If the slot is selected, the slot is (un)selected.
 */
fun GUI.unselectAllSlots() : GUI = copy(slots = slots.map { if(it.selected) it.select() else it})

fun GUI.checkClicks(me: MouseEvent, multiSelect: Boolean): GUI{
    var gui = this
    val clickedSlots = gui.slots.filter { it.isClicked(me) }
    val notClickedSlots = gui.slots - clickedSlots

    clickedSlots.forEach { slot ->
        //Select or unselect the current slot
        gui = gui.selectSlot(slot)
    }
    //If not multiselecting, verify if other slots are selected and unselect them (change color)
    if(!multiSelect && clickedSlots.isNotEmpty()) notClickedSlots.forEach { notClickedSlot ->
        if(notClickedSlot.selected) {
            gui = gui.selectSlot(notClickedSlot)
        }
    }
    return gui
}

fun GUI.addSlot(slot: Slot) = copy(slots = slots + slot)