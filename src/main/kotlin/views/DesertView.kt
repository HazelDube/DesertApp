package views

import models.DesertMemStore
import models.DesertModel



class DesertView {
    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add Desert")
        println(" 2. Update Desert")
        println(" 3. List All Deserts")
        println(" 4. Delete Desert")
        println(" 5. Search Deserts")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }


    fun listDeserts(deserts : DesertMemStore) {
        println("List AllDeserts")
        println()
       deserts.logAll()
        println()
    }

    fun showDesert(desert : DesertModel) {
        if(desert != null)
            println("Desert Details [ $desert ]")
        else
            println("Desert Not Found...")
    }

    fun addDesertData(desert : DesertModel) : Boolean {

        println()
        print("Enter a Title : ")
        desert.title = readLine()!!
        print("Enter a Description : ")
        desert.description = readLine()!!

        return desert.title.isNotEmpty() && desert.description.isNotEmpty()
    }

    fun updateDesertData(desert : DesertModel) : Boolean {

        var tempTitle: String?
        var tempDescription: String?

        if (desert != null) {
            print("Enter a new Title for [ " +desert.title + " ] : ")
            tempTitle = readLine()!!
            print("Enter a new Description for [ " + desert.description + " ] : ")
            tempDescription = readLine()!!

            if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
                desert.title = tempTitle
                desert.description = tempDescription
                return true
            }
        }
        return false
    }

    fun deleteDesert(desert : DesertModel) {
        if(desert != null)
            println("Desert Details [ $desert ]")
        else
            println("Desert Not Found...")

    }


    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}
