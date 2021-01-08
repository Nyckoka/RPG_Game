import pt.isel.canvas.*


data class Game(val player: Player, val state: String)


fun Canvas.drawGame(game: Game){
    erase()
    drawPlayer(game.player)

    if(game.state == "inventory"){
        drawGUI(game.player.inventory.gui)
    }
}


fun Game.openInventory() = changeState("inventory")
fun Game.changeState(state: String) = Game(player, state)

fun Game.movePlayer(dir: Char) = Game(player.move(dir), state)

fun Game.giveItemtoPlayer(item: Item) = Game(player.addItem(item), state)
fun Game.removeItemfromPlayer(name: String) = Game(player.removeItem(name), state)
fun Game.removeItemfromPlayer(id: Int) = Game(player.removeItem(id), state)


fun Game.removeSelectedItemfromInventory() : Game{
    var game = this
    if(state == "inventory"){
        player.inventory.gui.buttons.forEach { button ->
            if(button.color == CYAN && button.text != null){
                println("Item with name ${button.text.string} was removed")
                game = game.removeItemfromPlayer(button.text.string)
            }
        }
    }
    return game
}


fun Game.toggleInventory() : Game{
    return if(state != "inventory") openInventory()
    else changeState("playing")
}


fun Game.checkClicksinInventory(me: MouseEvent, multiSelect: Boolean) : Game{
    var game = this
    if(state == "inventory"){  //If the inventory is open
        val clickedButtons = player.inventory.gui.buttons.filter { it.isClicked(me) }
        clickedButtons.forEach { button ->
            //Select or unselect the current button
            game = game.copy(player = game.player.selectButtoninInventory(button, button.color != CYAN))
        }
        //If not multiselecting, verify if other buttons are selected and unselect them (change color)
        if(!multiSelect) (player.inventory.gui.buttons - clickedButtons).forEach { not_clickedButton ->
            if(not_clickedButton.color == CYAN)
                game = game.copy(player = game.player.selectButtoninInventory(not_clickedButton, false))
        }
    }
    return game
}