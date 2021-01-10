import pt.isel.canvas.*
import java.io.File


data class Game(val player: Player, val npcs: List<NPC>, val state: String, val map: List<List<Int>>)

fun Game.isInventoryOpen() = state == "inventory"

fun Game.openInventory() = changeState("inventory")
fun Game.closeInventory() = changeState("playing")

fun Game.changeState(state: String) = copy(state = state)

fun Game.movePlayer(dir: Char) = copy(player = player.move(dir))

fun Game.giveItemtoPlayer(item: Item) = copy(player = player.addItem(item))

fun Game.removeItemfromPlayer(name: String) = copy(player = player.removeItem(name))

fun Game.removeItemfromPlayer(id: Int) = copy(player = player.removeItem(id))

fun Game.checkInventoryClicks(me: MouseEvent, multiSelect: Boolean) = copy(player = player.checkInventoryClicks(me, multiSelect))

fun Game.removeInventorySelectedItems() : Game{
    var game = this
    player.inventory.gui.slots.forEach { slot ->
        if(slot.selected){
            println("Item with name ${player.inventory.items[slot.position].name} was removed")
            game = game.removeItemfromPlayer(slot.position)
        }
    }
    return game
}

fun Game.toggleInventory() : Game = if(state != "inventory") openInventory() else closeInventory()


fun loadMap(number: Int) : List<List<Int>>{
    val file = File("src/main/resources/Map$number/Tiles.txt")
    val lines = file.readLines()

    var map = listOf<List<Int>>()

    for(i in lines.indices){
        var lineMap = emptyList<Int>()

        for(j in lines[i].indices){
            lineMap = lineMap + listOf(when(lines[i][j]){
                'G' -> GREEN
                'B' -> BLUE
                else -> WHITE
            })
        }
        map = map + listOf(lineMap)
    }
    return map
}

fun loadNPCs(number: Int) : List<NPC>{
    val file = File("src/main/resources/Map$number/NPCs.txt")
    val lines = file.readLines()

    var npcs = listOf<NPC>()

    for(i in lines.indices){
        for(j in lines[i].indices){
            when(lines[i][j]){
                'B' -> npcs = npcs + NPC("Banker", Position(j, i), 100, emptyInventory())
                'V' -> npcs = npcs + NPC("Vendor", Position(j, i), 100, emptyInventory())
            }
        }
    }
    return npcs
}

fun Canvas.drawMap(map: List<List<Int>>){
    for(i in map.indices){
        for(j in map[i].indices){
            drawRect(j * TILE_SIDE, i * TILE_SIDE, TILE_SIDE, TILE_SIDE, map[i][j])
        }
    }
}

fun Canvas.drawNPCs(npcs : List<NPC>){
    npcs.forEach { drawNPC(it)}
}

fun Canvas.drawGame(game: Game){
    erase()
    drawMap(game.map)
    drawNPCs(game.npcs)
    drawPlayer(game.player)

    if(game.state == "inventory"){
        drawInventory(game.player.inventory)
        //drawGUI(game.player.inventory.gui)
    }
}

