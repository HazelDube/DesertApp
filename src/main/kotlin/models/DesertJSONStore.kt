package models


import Helpers.exists
import Helpers.read
import Helpers.write
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "Main.getDeserts.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<DesertModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class DesertJSONStore : DesertStore {

    var deserts = mutableListOf<DesertModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<DesertModel> {
        return deserts
    }

    override fun findOne(id: Long) :DesertModel? {
        var foundDesert: DesertModel? = deserts.find { p -> p.id == id }
        return foundDesert
    }

    override fun create(desert: DesertModel) {
        desert.id = generateRandomId()
        deserts.add(desert)
        serialize()
    }

    override fun update(desert: DesertModel) {
        var foundDesert = findOne(desert.id!!)
        if (foundDesert != null) {
            foundDesert.title = desert.title
            foundDesert.description =desert.description
        }
        serialize()
    }

    override fun delete(desert:DesertModel) {
        deserts.remove(desert)
        serialize()
    }

    internal fun logAll() {
        deserts.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson( deserts, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        deserts = Gson().fromJson(jsonString, listType)
    }
}