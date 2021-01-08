import pt.isel.canvas.*

data class Text(val position: Position, val string: String, val fontSize: Int, val color: Int = BLACK)
data class Button(val position: Position, val width: Int, val height: Int, val color: Int, val text: Text?, val clickable: Boolean)

fun Button.xRange() = position.x..position.x + width
fun Button.yRange() = position.y..position.y + height

fun Canvas.drawButtonRect(button: Button){
    button.apply { drawRect(position.x, position.y, width, height, color) }
}

fun Canvas.drawButtonText(button: Button){
    if(button.text != null) button.text.apply { drawText(position.x, position.y, string, color, fontSize) }
}

fun Button.isPointInside(x: Int, y: Int) = x in xRange() && y in yRange()

fun Button.isClicked(me: MouseEvent) = clickable && isPointInside(me.x, me.y)

fun Button.changeColor(color: Int) = copy(color = color)
fun Button.select() = changeColor(if(color != CYAN) CYAN else INVENTORY_WINDOW_COLOR)

data class GUI(val buttons: List<Button>)

fun GUI.selectButton(button: Button) = GUI(buttons - button + button.select())

fun GUI.checkClicks(me: MouseEvent, multiSelect: Boolean): GUI{
    var gui = this
    val clickedButtons = gui.buttons.filter { it.isClicked(me) }
    val notClickedButtons = gui.buttons - clickedButtons

    clickedButtons.forEach { button ->
        //Select or unselect the current button
        gui = gui.selectButton(button)
    }
    //If not multiselecting, verify if other buttons are selected and unselect them (change color)
    if(!multiSelect) notClickedButtons.forEach { not_clickedButton ->
        if(not_clickedButton.color == CYAN)
            gui = gui.selectButton(not_clickedButton)
    }
    return gui
}

fun Canvas.drawGUI(gui: GUI){
    gui.buttons.forEach { drawButtonRect(it) }
    gui.buttons.forEach { drawButtonText(it) }
}