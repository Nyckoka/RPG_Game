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
    if(state == "inventory"){
        player.inventory.gui.buttons.forEach { button ->
            if(button.color == CYAN && button.text != null)
                return removeItemfromPlayer(button.text.string)
        }
    }
    return this
}


fun Game.toggleInventory() : Game{
    return if(state != "inventory") openInventory()
    else changeState("playing")
}


fun Game.checkClicksinInventory(me: MouseEvent) : Game{
    var game = this
    if(state == "inventory"){  //If the inventory is open
        player.inventory.gui.buttons.forEach { button ->
            if(button.isClicked(me)){  //If the current button was clicked
                //Verify if other buttons are selected and unselect them (change color)
                (player.inventory.gui.buttons - button).forEach{ otherButton ->
                    if(otherButton.color == CYAN)
                        game = Game(player.selectButtoninInventory(otherButton, false), state)
                }
                //Select or unselect the current button
                game = Game(game.player.selectButtoninInventory(button, button.color != CYAN), game.state)
            }
        }
    }
    return game
}