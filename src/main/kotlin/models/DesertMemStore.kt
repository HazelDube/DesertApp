package models

import mu.KotlinLogging


    private val logger = KotlinLogging.logger {}
    var lastId = 0L

    internal fun getId(): Long {
        return lastId++
    }

    class  DesertMemStore : DesertStore {

        val deserts = ArrayList< DesertModel>()

        override fun findAll(): List< DesertModel> {
            return deserts
        }

        override fun findOne(id: Long) : DesertModel? {
            var foundDesert:  DesertModel? = deserts.find { p -> p.id == id }
            return foundDesert
        }

        override fun create(desert: DesertModel) {
            desert.id = getId()
            deserts.add(desert)
            logAll()
        }

        override fun update(desert: DesertModel) {
            var foundDesert = findOne(desert.id!!)
            if (foundDesert != null) {
                foundDesert.title = desert.title
                foundDesert.description =desert.description
            }
        }

        override fun delete(desert: DesertModel) {
            deserts.remove(desert)
        }

        internal fun logAll() {
            deserts.forEach { logger.info("${it}") }
        }

    }