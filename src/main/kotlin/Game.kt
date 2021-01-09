import pt.isel.canvas.*


data class Game(val player: Player, val state: String)


fun Canvas.drawGame(game: Game){
    erase()
    drawPlayer(game.player)

    if(game.state == "inventory"){
        drawGUI(game.player.inventory.gui)
    }
}

fun Game.isInventoryOpen() = state == "inventory"

fun Game.openInventory() = changeState("inventory")
fun Game.changeState(state: String) = Game(player, state)

fun Game.movePlayer(dir: Char) = Game(player.move(dir), state)

fun Game.giveItemtoPlayer(item: Item) = Game(player.addItem(item), state)
fun Game.removeItemfromPlayer(name: String) = Game(player.removeItem(name), state)
fun Game.removeItemfromPlayer(id: Int) = Game(player.removeItem(id), state)

fun Game.checkInventoryClicks(me: MouseEvent, multiSelect: Boolean) = copy(player = player.checkInventoryClicks(me, multiSelect))

fun Game.removeInventorySelectedItems() : Game{
    var game = this
    player.inventory.gui.buttons.forEach { button ->
        if(button.color == CYAN && button.text != null){
            println("Item with name ${button.text.string} was removed")
            game = game.removeItemfromPlayer(button.text.string)
        }
    }
    return game
}


fun Game.toggleInventory() : Game{
    return if(state != "inventory") openInventory()
    else changeState("playing")
}



