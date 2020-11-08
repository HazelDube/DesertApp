package models

interface DesertStore {

        fun findAll(): List<DesertModel>
        fun findOne(id: Long): DesertModel?
        fun create(desert: DesertModel)
        fun update(desert: DesertModel)
         fun delete(desert: DesertModel)
    }
