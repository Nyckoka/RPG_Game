import pt.isel.canvas.*


data class Game(val player: Player, val state: String)

fun Canvas.drawGame(game: Game){
    erase()
    drawPlayer(game.player)

    if(game.state == "inventory"){
        drawInventory(game)
    }
}

fun Game.openInventory() = changeState("inventory")
fun Game.changeState(state: String) = Game(player, state)

fun Game.movePlayer(dir: Char) = Game(player.move(dir), state)

fun Game.giveItemtoPlayer(item: Item) = Game(player.addItem(item), state)
fun Game.removeItemfromPlayer(name: String) = Game(player.removeItem(name), state)
fun Game.removeItemfromPlayer(id: Int) = Game(player.removeItem(id), state)