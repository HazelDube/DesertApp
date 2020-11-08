package controllers



import models.DesertMemStore
import models.DesertModel
import views.DesertView
import mu.KotlinLogging



class DesertController {

   val deserts = DesertMemStore()
  // val Main.getDeserts = DesertJSONStore()
    val desertView = DesertView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Desert Console App" }
        println("Desert Kotlin App Version 3.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> delete()
                5 -> search()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Desert Console App" }
    }

    fun menu() :Int { return desertView.menu() }

    fun add(){
        var aDesert = DesertModel()

        if (desertView.addDesertData(aDesert))
            deserts.create(aDesert)
        else
            logger.info("Desert Not Added")
    }

    fun list() {
        desertView.listDeserts(deserts)
    }

    fun update() {

        desertView.listDeserts(deserts)
        var searchId = desertView.getId()
        val aDesert = search(searchId)

        if(aDesert != null) {
            if(desertView.updateDesertData(aDesert)) {
                deserts.update(aDesert)
                desertView.showDesert(aDesert)
                logger.info("Desert Updated : [ $aDesert]")
            }
            else
                logger.info("Desert Not Updated")
        }
        else
            println("Desert Not Updated...")
    }

    fun delete() {
        desertView.listDeserts(deserts)
        var searchId = desertView.getId()
        val aDesert = search(searchId)

        if(aDesert != null) {
           deserts.delete(aDesert)
            println("Desert Deleted...")
            desertView.listDeserts(deserts)
        }
        else
            println("desert Not Deleted...")
    }


    fun search() {
        val aDesert = search(desertView.getId())!!
        desertView.showDesert(aDesert)
    }


    fun search(id: Long) :DesertModel? {
        var foundDesert = deserts.findOne(id)
        return foundDesert
    }

    fun dummyData() {
         deserts.create(DesertModel(title = "New York New York", description = "So Good They Named It Twice"))
        deserts.create(DesertModel(title= "Ring of Kerry", description = "Some place in the Kingdom"))
        deserts.create(DesertModel(title = "Waterford City", description = "You get great Blaas Here!!"))
    }
    }