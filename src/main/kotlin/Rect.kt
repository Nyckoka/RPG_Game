import pt.isel.canvas.*

data class Text(val position: Position, val string: String, val fontSize: Int, val color: Int = BLACK)

open class Rect(val position: Position, val width: Int, val height: Int, val color: Int, val text: Text?){
    /*fun copy(position: Position = this.position, width: Int = this.width, height: Int = this.height, color: Int = this.color,
                  text: Text? = this.text) =
        Rect(position, width, height, color, text)*/
}

fun Rect.xRange() = position.x..position.x + width
fun Rect.yRange() = position.y..position.y + height

fun Rect.isPointInside(x: Int, y: Int) = x in xRange() && y in yRange()


fun Canvas.drawRectShape(button: Rect){
    button.apply { drawRect(position.x, position.y, width, height, color) }
}

fun Canvas.drawRectText(button: Rect){
    if(button.text != null) button.text.apply { drawText(position.x, position.y, string, color, fontSize) }
}

class Slot(position: Position, width: Int, height: Int, color: Int, text: Text?, val clickable: Boolean)
    : Rect(position, width, height, color, text)

fun Slot.isClicked(me: MouseEvent) = clickable && isPointInside(me.x, me.y)

fun Slot.copy(position: Position = this.position, width: Int = this.width, height: Int = this.height, color: Int = this.color,
         text: Text? = this.text, clickable: Boolean = this.clickable)
        = Slot(position, width, height, color, text, clickable)

fun Slot.changeColor(color: Int) = copy(color = color)

fun Slot.select() = changeColor(if(color != CYAN) CYAN else INVENTORY_WINDOW_COLOR)