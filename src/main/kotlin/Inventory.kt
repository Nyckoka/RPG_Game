

class Item(val name: String, val value: Int){

}

class Inventory(val items: List<Item>) {

}

fun Inventory.addItem(item: Item) : Inventory{
    return Inventory(items + item)
}

fun Inventory.showItems(){
    for(item in items){
        item.apply { println("Name: $name - Value: $value") }
    }
}