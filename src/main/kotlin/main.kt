import pt.isel.canvas.*


const val TILE_SIDE = 20
const val WIDTH = 60
const val HEIGHT = 40
const val TRUE_WIDTH = WIDTH * TILE_SIDE
const val TRUE_HEIGHT = HEIGHT * TILE_SIDE

const val MAP_NUMBER = 1
const val PLAYER_NAME = "Knight"

fun emptyInventory() = Inventory(emptyList())

fun loadPlayer() : Player = Player(PLAYER_NAME, Position(0, 0), 100, emptyInventory())

fun loadGame() : Game = Game(
    player = loadPlayer(),
    npcs = loadNPCs(MAP_NUMBER),
    state = "playing",
    map = loadMap(MAP_NUMBER),
    gui = GUI(emptyList()))

fun main(){
    var game = loadGame()
    val arena = Canvas(WIDTH * TILE_SIDE, HEIGHT * TILE_SIDE + 5, GREEN)

    game = game.giveItemtoPlayer(Item("Golden Crown", 100))
    game = game.giveItemtoPlayer(Item("Sparking Dust", 30))
    game = game.giveItemtoPlayer(Item("Pau Mágico", 69))
    game = game.giveItemtoPlayer(Item("Cajado Magia", 230))
    game = game.giveItemtoPlayer(Item("Orb Maldito", 0))
    game = game.giveItemtoPlayer(Item("Pedra de Crack", 420))
    game = game.giveItemtoPlayer(Item("Amuleto da Sorte", 7))
    game = game.giveItemtoPlayer(Item("Pó dos Sonhos", 11))

    onStart {
        arena.onTimeProgress(10){
            arena.drawGame(game)
        }

        arena.onKeyPressed { ke ->
            when(ke.char){
                in "wasd" -> if(game.state == "playing") game = game.movePlayer(ke.char)
                'i' -> game = game.toggleInventory()
                'f' -> if(game.isInventoryOpen()) game = game.removeInventorySelectedItems()
                'p' -> game = game.giveItemtoPlayer(Item("Pedra Múltipla${game.player.inventory.items.size}", game.player.inventory.items.size))
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