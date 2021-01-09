import pt.isel.canvas.*


const val TILE_SIDE = 20
const val WIDTH = 20
const val HEIGHT = 20
const val TRUE_WIDTH = WIDTH * TILE_SIDE
const val TRUE_HEIGHT = HEIGHT * TILE_SIDE

fun main(){
    var game = Game(Player("Knight", Position(0, 0), 100, Inventory(emptyList(), GUI(emptyList()))).updateInventoryGUI(), "playing")
    val arena = Canvas(WIDTH * TILE_SIDE, HEIGHT * TILE_SIDE + 5, GREEN)

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
                game = game.toggleInventory()
            }
            else if(ke.char == 'f'){
                if(game.isInventoryOpen()) game = game.removeInventorySelectedItems()
            }
        }

        arena.onMouseDown { me ->
            if(game.isInventoryOpen()) game = game.checkInventoryClicks(me, false)
        }

    }
    onFinish {

    }
}