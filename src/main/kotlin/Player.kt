import pt.isel.canvas.*

data class Player (val name: String, val position: Position, val health: Int, val inventory: Inventory)

fun Player.move(dir : Char) = copy(position = Position(position.x + if(dir == 'd') 1 else if(dir == 'a') -1 else 0,
    position.y + if(dir == 's') 1 else if(dir == 'w') -1 else 0))

fun Player.addItem(item: Item) = copy(inventory = inventory.addItem(item))

fun Player.removeItem(itemName: String) = copy(inventory = inventory.removeItem(itemName))

fun Player.removeItem(id: Int) = copy(inventory = inventory.removeItem(id))

fun Canvas.drawPlayer(player: Player){
    player.apply {
        val drawPositionX = position.x * TILE_SIDE
        val drawPositionY = position.y * TILE_SIDE
        val scale = 2
        val HAIR_COLOR = 0x974807
        val SKIN_COLOR = 0xFAC090
        val BLUE_SHIRT_COLOR = 0x0070C0

        fun drawRect2(x: Int, y: Int, width: Int, height: Int, color: Int){
            drawRect((drawPositionX + x) * scale, (drawPositionY + y) * scale, width * scale, height * scale, color)
        }

        //Hat
        drawRect2(7, 2, 6, 1, RED)
        drawRect2(6, 3, 10, 1, RED)

        //Hair
        drawRect2(6, 4, 3, 1, HAIR_COLOR)
        drawRect2(5, 5, 1, 3, HAIR_COLOR)
        drawRect2(7, 5, 1, 1, HAIR_COLOR)
        drawRect2(7, 6, 2, 1, HAIR_COLOR)
        drawRect2(6, 7, 1, 1, HAIR_COLOR)

        //Head/Face
        drawRect2(6, 5, 1, 2, SKIN_COLOR)
        drawRect2(9, 4, 3, 1, SKIN_COLOR)
        drawRect2(8, 5, 4, 1, SKIN_COLOR)
        drawRect2(9, 6, 4, 1, SKIN_COLOR)
        drawRect2(7, 7, 5, 1, SKIN_COLOR)
        drawRect2(7, 8, 8, 1, SKIN_COLOR)
        drawRect2(13, 4, 1, 1, SKIN_COLOR)
        drawRect2(13, 5, 3, 1, SKIN_COLOR)
        drawRect2(14, 6, 3, 1, SKIN_COLOR)
        drawRect2(7, 7, 5, 1, SKIN_COLOR)
        drawRect2(7, 7, 5, 1, SKIN_COLOR)

        //Mustache and Eye
        drawRect2(12, 4, 1, 2, BLACK)
        drawRect2(13, 6, 1, 1, BLACK)
        drawRect2(12, 7, 4, 1, BLACK)

        //Red Shirt
        drawRect2(6, 9, 2, 1, RED)
        drawRect2(9, 9, 4, 1, RED)
        drawRect2(5, 10, 3, 1, RED)
        drawRect2(9, 10, 2, 1, RED)
        drawRect2(12, 10, 3, 1, RED)
        drawRect2(4, 11, 4, 1, RED)
        drawRect2(12, 11, 4, 1, RED)
        drawRect2(6, 12, 1, 1, RED)
        drawRect2(13, 12, 1, 1, RED)

        //Blue Clothes
        drawRect2(8, 9, 1, 3, BLUE_SHIRT_COLOR)
        drawRect2(9, 11, 3, 1, BLUE_SHIRT_COLOR)
        drawRect2(11, 10, 1, 1, BLUE_SHIRT_COLOR)
        drawRect2(6, 14, 1, 2, BLUE_SHIRT_COLOR)
        drawRect2(7, 12, 1, 4, BLUE_SHIRT_COLOR)
        drawRect2(8, 13, 1, 3, BLUE_SHIRT_COLOR)
        drawRect2(9, 12, 2, 3, BLUE_SHIRT_COLOR)
        drawRect2(11, 13, 1, 3, BLUE_SHIRT_COLOR)
        drawRect2(12, 12, 1, 4, BLUE_SHIRT_COLOR)
        drawRect2(13, 14, 1, 2, BLUE_SHIRT_COLOR)

        //Golden Buttons
        drawRect2(8, 12, 1, 1, YELLOW)
        drawRect2(11, 12, 1, 1, YELLOW)

        //Hands
        drawRect2(4, 12, 2, 3, SKIN_COLOR)
        drawRect2(6, 13, 1, 1, SKIN_COLOR)
        drawRect2(14, 12, 2, 3, SKIN_COLOR)
        drawRect2(13, 13, 1, 1, SKIN_COLOR)

        //Boots
        drawRect2(5, 16, 3, 1, HAIR_COLOR)
        drawRect2(4, 17, 4, 1, HAIR_COLOR)
        drawRect2(12, 16, 3, 1, HAIR_COLOR)
        drawRect2(12, 17, 4, 1, HAIR_COLOR)
    }
}
