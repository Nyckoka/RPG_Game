import pt.isel.canvas.*
import java.io.File


data class Game(val player: Player, val npcs: List<NPC>, val state: String, val map: List<List<Int>>, val gui: GUI)

fun Game.isInventoryOpen() = state == "inventory"

fun Game.openInventory() = changeState("inventory")

fun Game.closeInventory() : Game = copy(state = "playing", gui = gui.unselectAllSlots())

fun Game.changeState(state: String) = copy(state = state)

fun Game.movePlayer(dir: Char) = copy(player = player.move(dir))

//Manage Items

fun Game.giveItemtoPlayer(item: Item) = copy(player = player.addItem(item)).updateInventoryItemSlots()

fun Game.removeItemfromPlayer(name: String) = copy(player = player.removeItem(name)).updateInventoryItemSlots()

fun Game.removeItemfromPlayer(id: Int) = copy(player = player.removeItem(id)).updateInventoryItemSlots()

//Verify interaction with GUI

fun Game.checkInventoryClicks(me: MouseEvent, multiSelect: Boolean) = copy(gui = gui.checkClicks(me, multiSelect))

fun Game.removeInventorySelectedItems() : Game{
    var game = this

    gui.slots.forEach { slot ->
        if(slot.selected){
            val itemName = player.inventory.items[slot.position].name
            println("Item with name $itemName was removed")
            game = game.removeItemfromPlayer(itemName)
        }
    }
    return game
}



fun Game.toggleInventory() : Game = if(state != "inventory") openInventory() else closeInventory()


//Load initial objects
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

//Draw game

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
        drawPlayerInventory(game)
        //drawGUI(game.player.inventory.gui)
    }
}

