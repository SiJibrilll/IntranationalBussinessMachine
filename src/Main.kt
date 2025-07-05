import java.lang.System // ✅ Add this if System.out gives you an error
import kotlin.text.toInt

class Sales(var value:Long = 0, var date:Int = 1, var month:Int = 1,  var year: Int = 1 )

class System {
    val sales = mutableListOf<Sales>()

    fun addSales( value:Long = 0,  date:Int = 1, month:Int = 1,  year: Int = 1) {
        sales.add(Sales(value, date, month, year))
    }

    fun updateSales(index:Int, value:Long = -1L,  date:Int = -1, month:Int = -1,  year: Int = -1) {
        var inputs: Array<Number> = arrayOf(value, date, month, year)
        var collumns = arrayOf("value", "date", "month", "year")

        for ((i, column) in collumns.withIndex()) {
            when (column) {
                "value" -> sales[index].value = if (inputs[i] == -1) sales[index].value else inputs[i].toLong()
                "date" -> sales[index].date = if (inputs[i] == -1) sales[index].date else inputs[i].toInt()
                "month" -> sales[index].month = if (inputs[i] == -1) sales[index].month else inputs[i].toInt()
                "year" -> sales[index].year = if (inputs[i] == -1) sales[index].year else inputs[i].toInt()
            }
        }

    }

    fun deleteSales(index:Int) {
        sales.removeAt(index)
    }

    fun indexSales() {
        if (sales.size < 1) {
            println("No sales has been made!")
            return
        }
        
        for ((index, sale) in sales.withIndex()) {
            var value = sale.value
            var date = sale.date
            var month = sale.month
            var year = sale.year
            println("[$index] Sale of $value in $date/$month/$year")
        }
    }

    fun getSales(index:Int):Sales {
        return sales[index]
    }
}

fun clearConsole() {
    val os = System.getProperty("os.name") // Make sure 'System.' is here
    if (os.contains("Windows", ignoreCase = true)) {
        ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor()
    } else {
        ProcessBuilder("clear").inheritIO().start().waitFor()
    }
}

fun line() {
    println("-----------------------------------------------")
}

fun confirm() {
    println("Enter any key to continue")
    readLine()
}
fun main() {
    val system = System()
    val menus = arrayOf("index", "add", "update", "delete", "exit")

    while (true) {
        clearConsole()
        
        println("= = = = INTRANATIONAL BUSSINESS MACHINE = = = =")
    
        for ((i, menu) in menus.withIndex()) {
            println("[$i] $menu")
        }
        line()
        println("Please select a menu: ")
        var select = readLine()

        clearConsole()
    
        if (select == null || select.equals("")) {
            println("Invalid menu selected")
            confirm()
            continue
        }
    
        
    
        when (menus[select.toInt()]) {
            "index" -> {
                system.indexSales()
                line()
                confirm()
            }

            "add" -> {
                var input:String
                var error = false

                var value:Long
                var date:Int
                var month:Int
                var year:Int

                println("Input sale value: ")
                value = readLine()?.toLongOrNull() ?: -1L

                println("Input date value: ")
                date = readLine()?.toIntOrNull() ?: -1
              
                println("Input month value: ")
                month = readLine()?.toIntOrNull() ?: -1
              
                println("Input year value: ")
                year = readLine()?.toIntOrNull() ?: -1

                if (value < 0L || date < 0 || month < 0 || year < 0) {
                    println("Invalid inputs")
                    confirm()
                    continue
                }

                system.addSales(value, date, month, year)

                clearConsole()
                println("Sale added: ")
                println("Sale value of: $value in $date/$month/$year")
                line()
                confirm()
              
            }

            "update" -> {
                system.indexSales()
                line()
                println("Select index of sale to edit: ")
                var input = readLine()?.toIntOrNull() ?: -1

                clearConsole()

                if (input == -1) {
                    println("Invalid index!")
                    line()
                    confirm()
                    continue
                }

                var value:Long
                var date:Int
                var month:Int
                var year:Int
                
                var sale = system.getSales(input)
                println("Input new sale value: (Leave empty to use old data: ${sale.value})")
                value = readLine()?.toLongOrNull() ?: -1L

                println("Input new date value: (Leave empty to use old data ${sale.date})")
                date = readLine()?.toIntOrNull() ?: -1
              
                println("Input new month value: (Leave empty to use old data: ${sale.month})")
                month = readLine()?.toIntOrNull() ?: -1
              
                println("Input new year value: (Leave empty to use old data: ${sale.year})")
                year = readLine()?.toIntOrNull() ?: -1

                system.updateSales(input, value, date, month, year)

                clearConsole()
                println("Sale updated!")

                sale = system.getSales(input)
                println("[$input] Sale of: ${sale.value} in ${sale.date}/${sale.month}/${sale.year}")
                line()
                confirm()
                continue
            }

            "delete" -> {
                println("Select a sale index to delete")
                system.indexSales()
                line()
                var input = readLine()?.toIntOrNull() ?: -1

                clearConsole()

                
                if (input == -1 || input >= system.sales.size) {
                    println("Invalid index")
                    line()
                    confirm()
                    continue
                }

                var sale = system.getSales(input)
                system.deleteSales(input)

                println("Delete completed!")
                println("Sale [$input] of: ${sale.value} in ${sale.date}/${sale.month}/${sale.year} has been deleted!")
                line()
                confirm()
            }

            "exit" -> {
                println("Good Bye!")
                break
            }
    
        }
    }
}