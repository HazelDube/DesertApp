package Main

import models.DesertMemStore
import models.DesertModel
import views.DesertView
import mu.KotlinLogging


private val logger = KotlinLogging.logger {}

val deserts = DesertMemStore()
val desertView = DesertView()


fun main(args: Array<String>) {
    logger.info { "Launching Desert Console App" }
    println("Desert Kotlin App Version 1.0")

    var input: Int

    do {
        input = desertView.menu()
        when(input) {
            1 -> addDesert()
            2 -> updateDesert()
            3 -> desertView.listDeserts(deserts)
            4 -> deleteDesert()
            5 -> searchDesert()
            -99 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Placemark Console App" }
}

fun deleteDesert() {
    desertView.listDeserts(deserts)
    var searchId = desertView.getId()
    val aDesert = search(searchId)

    if(aDesert != null) {
        deserts.delete(aDesert)
        println("Desert Deleted...")
        desertView.listDeserts(deserts)
    }
    else
        println("Desert Not Deleted...")
}


fun addDesert(){
    var aDesert = DesertModel()

    if (desertView.addDesertData(aDesert))
        deserts.create(aDesert)
    else
        logger.info("Desert Not Added")
}

fun updateDesert() {

    desertView.listDeserts(deserts)
    var searchId = desertView.getId()
    val aDesert = search(searchId)

    if(aDesert != null) {
        if(desertView.updateDesertData(aDesert)) {
            deserts.update(aDesert)
            desertView.showDesert(aDesert)
            logger.info("Desert Updated : [ $aDesert ]")
        }
        else
            logger.info("Desert Not Updated")
    }
    else
        println("Desert Not Updated...")
}

fun searchDesert() {
    val aDesert = search(desertView.getId())!!
    desertView.showDesert(aDesert)
}


fun search(id: Long) : DesertModel? {
    var foundDesert = deserts.findOne(id)
    return foundDesert
}

fun dummyData() {
    deserts.create(DesertModel(title = "New York New York", description = "So Good They Named It Twice"))
    deserts.create(DesertModel(title= "Ring of Kerry", description = "Some place in the Kingdom"))
    deserts.create(DesertModel(title = "Waterford City", description = "You get great Blaas Here!!"))
}
