package com.example

import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass
import org.jetbrains.exposed.v1.core.dao.id.EntityID

class Sports(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Sports>(SportsTable)

    var uid by SportsTable.uid
    var sportId by SportsTable.sportId
    var sportName by SportsTable.sportName
    var sportDescription by SportsTable.sportDescription

    override fun toString() = sportName
}