import pt.isel.canvas.*

data class Button(val position: Position, val width: Int, val height: Int, val color: Int, val text: String, val clickable: Boolean)

fun Canvas.drawButton(button: Button){
    button.apply { drawRect(position.x, position.y, width, height, color) }
    if(button.text.isNotEmpty()) drawText(button.position.x, button.position.y, button.text, BLACK, 20)
}


fun Button.isClicked(me: MouseEvent) : Boolean{
    return clickable && me.x in position.x..position.x + width && me.y in position.y..position.y + height
}


data class GUI(val buttons: List<Button>)
fun Canvas.drawGUI(gui: GUI){
    gui.buttons.forEach { drawButton(it) }
}