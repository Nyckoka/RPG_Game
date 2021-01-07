


fun main(){
    var game = Game(Player("Knight", Position(1, 4), 100, Inventory(emptyList())))

    val goldenCrown = Item("Golden Crown", 100)

    game = game.giveItemtoPlayer(Item("Golden Crown", 100))
    game = game.giveItemtoPlayer(Item("Sparking Dust", 30))

    game.player.showItems()
}