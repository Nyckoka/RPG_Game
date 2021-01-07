

data class Game(val player: Player)

fun Game.giveItemtoPlayer(item: Item) = Game(player.addItem(item))