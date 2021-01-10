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


data class Slot(val position: Int, val selected: Boolean)

fun Slot.yPos() = windowPosition.y + TILE_SIDE * (3 + position)

fun Slot.xRange() = windowPosition.x..windowPosition.x + inventoryWindowWidth
fun Slot.yRange() = yPos()..yPos() + TILE_SIDE

fun Slot.isPointInside(x: Int, y: Int) = x in xRange() && y in yRange()

fun Slot.isClicked(me: MouseEvent) = isPointInside(me.x, me.y)

fun Slot.select() = copy(selected = true)
fun Slot.unselect() = copy(selected = false)