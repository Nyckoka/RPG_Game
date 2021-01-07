import pt.isel.canvas.*

data class Text(val position: Position, val string: String)
data class Button(val position: Position, val width: Int, val height: Int, val color: Int, val text: Text?, val clickable: Boolean)

fun Canvas.drawButtonRect(button: Button){
    button.apply { drawRect(position.x, position.y, width, height, color) }
}

fun Canvas.drawButtonText(button: Button){
    if(button.text != null) drawText(button.text.position.x, button.text.position.y, button.text.string, BLACK, 20)
}


fun Button.isClicked(me: MouseEvent) : Boolean{
    return clickable && me.x in position.x..position.x + width && me.y in position.y..position.y + height
}


data class GUI(val buttons: List<Button>)
fun Canvas.drawGUI(gui: GUI){
    gui.buttons.forEach { drawButtonRect(it) }
    gui.buttons.forEach { drawButtonText(it) }
}