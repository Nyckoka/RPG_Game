import pt.isel.canvas.*



const val TILE_SIDE = 20

fun main(){
    var game = Game(Player("Knight", Position(0, 0), 100, Inventory(emptyList())), "playing")
    val arena = Canvas(50 * TILE_SIDE, 20 * TILE_SIDE + 5, BLACK)

    game = game.giveItemtoPlayer(Item("Golden Crown", 100))
    game = game.giveItemtoPlayer(Item("Sparking Dust", 30))
    game = game.giveItemtoPlayer(Item("Pau Mágico", 69))

    onStart {

        arena.onTimeProgress(10){
            arena.drawGame(game)
        }

        arena.onKeyPressed { ke ->
            if(ke.char in "wasd" && game.state == "playing"){
                game = game.movePlayer(ke.char)
            }
            else if(ke.char == 'i'){
                if(game.state != "inventory") {
                    game = game.openInventory()
                }
                else{
                    game = game.changeState("playing")
                }
            }
            else if(ke.char == 'f'){
                game = game.removeItemfromPlayer(0)
            }
        }

        arena.onMouseDown { me ->
            if(game.state == "inventory"){

            }
        }

    }
    onFinish {

    }
}