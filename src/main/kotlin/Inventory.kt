

class Item(val name: String, val value: Int){

}

class Inventory(val items: List<Item>) {

}

fun Inventory.addItem(item: Item) = Inventory(items + item)

fun Inventory.removeItem(name: String) : Inventory{
    for(item in items){
        if(item.name == name) return Inventory(items - item)
    }
    println("There's no item with the name \"$name\" in the inventory!")
    return this
}
fun Inventory.removeItem(id: Int) : Inventory{
    try{ return Inventory(items - items[id]) }
    catch (e: Exception){ println("There's no item with the id $id in the inventory!") }
    return this
}

fun Inventory.showItems(){
    for(item in items){
        item.apply { println("Name: $name - Value: $value") }
    }
}