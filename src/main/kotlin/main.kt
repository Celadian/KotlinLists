fun main(args: Array<String>) {
    println("\nBeginning Int List example \n")
    ints()
    println("\nBeginning String List example \n")
    colors()
    println("\nBeginning unsorted List example \n")
    unsortedList()
    //everything above is a READ ONLY LIST. Below is working with READ/WRITE lists or "mutable"
    println("\nBeginning \"Mutable Lists\" example \n")
    mutableLists()
    println("\nBeginning \"looping through lists\" example \n")
    loopingLists()
    println("\nBeginning \"For'ing through lists?\" example \n")
    foringLists()
    println("\nPutting it all together to order food. BUCKLE IN\n")
    orderingFood()
    println("\nApplication Complete")

}

fun ints(){
    val numbers: List<Int> = listOf(1, 2, 4, 5, 6)
    //recall $variable is the same as variable.toString()
    println("List: $numbers")
    //If its using a function enclose it with {} to convert to string. Akin to numbers.size.toString()
    println("Size: ${numbers.size}")
    //below are different techniques for searching lists
    println("Second element: ${numbers[1]}")
    //this one is confusing but it is just counting the amount of items in the list and offsetting it to find the index of the last number
    println("Last index: ${numbers.size - 1}")
    println("Last element: ${numbers[numbers.size - 1]}")
    println("First: ${numbers.first()}")
    println("Last: ${numbers.last()}")
    //contains looks for what its looking for ja feel
    println("Contains 4? ${numbers.contains(4)}")
    println("Contains 7? ${numbers.contains(7)}")
}

fun colors(){
    //examples of how you CAN modify read only lists. In my opinion this is just formatting not actually modifying
    val colors = listOf("green", "orange", "blue")
    println("Reversed list: ${colors.reversed()}")
    println("List: $colors")
    println("Sorted list: ${colors.sorted()}")
}

fun unsortedList(){
    val oddNumbers = listOf(5, 3, 7, 1)
    println("List: $oddNumbers")
    println("Sorted list: ${oddNumbers.sorted()}")
}

fun mutableLists(){
    //creation of a "mutable" list
    val entrees: MutableList<String> = mutableListOf()
    println("Entrees: $entrees")
    //appending data
    println("Add noodles: ${entrees.add("noodles")}")
    println("Entrees: $entrees")
    println("Add spaghetti: ${entrees.add("spaghetti")}")
    println("Entrees: $entrees")
    //adding a normal list to a mutable list. CANNOT be done the other way around. listOf() does not have add functionality
    val moreItems = listOf("ravioli", "lasagna", "fettuccine")
    //adding in the list here
    println("Add list: ${entrees.addAll(moreItems)}")
    println("Entrees: $entrees")
    //removing elements from list by search or by index
    println("Remove spaghetti: ${entrees.remove("spaghetti")}")
    println("Entrees: $entrees")
    println("Remove item that doesn't exist: ${entrees.remove("rice")}")
    println("Entrees: $entrees")
    println("Remove first element: ${entrees.removeAt(0)}")
    println("Entrees: $entrees")
    //for that moment when you want to be alone
    entrees.clear()
    println("Entrees: $entrees")
    //Simple boolean answer to a simple question of your list's loneliness
    println("Empty? ${entrees.isEmpty()}")
}

fun loopingLists(){
    val guestsPerFamily = listOf(2, 4, 1, 3)
    var totalGuests = 0
    var index = 0
    //when all the languages of the old world are dead. ++,+= will transcend time and space
    while (index < guestsPerFamily.size) {
        totalGuests += guestsPerFamily[index]
        index++
    }
    println("Total Guest Count: $totalGuests")
}

fun foringLists(){
    val names = listOf("Jessica", "Henry", "Alicia", "Jose")
    //take note that nothing changed in the list. This is non mutable. Its simply formatting the output
    for (name in names) {
        println("$name - Number of characters: ${name.length}")
    }
}

fun orderingFood(){ //classes for orders and vegetables are below this function
    val ordersList = mutableListOf<Order>()

    // creates a value called order1 then calls the Order class
    val order1 = Order(1)
    // adds Noodles to order 1. Then adds order1 to the order list
    order1.addItem(Noodles())
    ordersList.add(order1)

    // Add multiple items individually
    val order2 = Order(2)
    order2.addItem(Noodles())
    order2.addItem(Vegetables())
    ordersList.add(order2)

    // Add a list of items at one time
    val order3 = Order(3)
    val items = listOf(Noodles(), Vegetables("Carrots", "Beans", "Celery"))
    order3.addAll(items)
    ordersList.add(order3)

    // Use builder pattern
    val order4 = Order(4)
        .addItem(Noodles())
        .addItem(Vegetables("Cabbage", "Onion"))
    ordersList.add(order4)

    // Create and add order directly
    ordersList.add(
        Order(5)
            .addItem(Noodles())
            .addItem(Noodles())
            .addItem(Vegetables("Spinach"))
    )

    // Print out each order
    for (order in ordersList) {
        order.print()
        println()
    }
}
//this is our base class of which where orders will inherit. Every item has a name and a price
open class Item(val name: String, val price: Int)

//The noodle class inherits item because it is an item. So it has a name and a price. It returns a name
class Noodles : Item("Noodles", 10) {
    override fun toString(): String {
        return name
    }
}

//The vegetables class inherit Item so they have a name and a price but also take in sepcific toppings. Returns Chef's choice or a list of veggies
class Vegetables(vararg val toppings: String) : Item("Vegetables", 5) {
    override fun toString(): String {
        if (toppings.isEmpty()) {
            return "$name Chef's Choice"
        } else {
            return name + " " + toppings.joinToString()
        }
    }
}

//The order class does not inherit items because it is not an item
class Order(val orderNumber: Int) {
    private val itemList = mutableListOf<Item>()
    //adds an item to the list then returns whatever is added
    fun addItem(newItem: Item): Order {
        itemList.add(newItem)
        return this
    }
    //same here except this returns everything (Not individually picked items)
    fun addAll(newItems: List<Item>): Order {
        itemList.addAll(newItems)
        return this
    }
    //prints the complete order and all its individual parts.
    fun print() {
        println("Order #${orderNumber}")
        var total = 0
        for (item in itemList) {
            println("${item}: $${item.price}")
            total += item.price
        }
        println("Total: $${total}")
    }
}