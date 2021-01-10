import pt.isel.canvas.*


const val TILE_SIDE = 20
const val WIDTH = 60
const val HEIGHT = 40
const val TRUE_WIDTH = WIDTH * TILE_SIDE
const val TRUE_HEIGHT = HEIGHT * TILE_SIDE

const val MAP_NUMBER = 1
const val PLAYER_NAME = "Knight"

fun emptyInventory() = Inventory(emptyList(), GUI(emptyList()))

fun main(){
    var game = Game(player = Player("Knight", Position(0, 0), 100, emptyInventory()),
                    npcs = loadNPCs(MAP_NUMBER),
                    state = "playing",
                    map = loadMap(MAP_NUMBER))
    val arena = Canvas(WIDTH * TILE_SIDE, HEIGHT * TILE_SIDE + 5, GREEN)

    game = game.giveItemtoPlayer(Item("Golden Crown", 100))
    game = game.giveItemtoPlayer(Item("Sparking Dust", 30))
    game = game.giveItemtoPlayer(Item("Pau Mágico", 69))

    onStart {
        arena.onTimeProgress(10){
            arena.drawGame(game)
        }

        arena.onKeyPressed { ke ->
            when(ke.char){
                in "wasd" -> if(game.state == "playing") game = game.movePlayer(ke.char)
                'i' -> game = game.toggleInventory()
                'f' -> if(game.isInventoryOpen()) game = game.removeInventorySelectedItems()
                //'m' -> Show mini map
            }
        }

        arena.onMouseDown { me ->
            if(game.isInventoryOpen()) game = game.checkInventoryClicks(me, false)
        }

    }
    onFinish {

    }
}