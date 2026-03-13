package com.physicalfitness

import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass
import org.jetbrains.exposed.v1.core.dao.id.EntityID

class Sports(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Sports>(SportsTable)

    var sportId by SportsTable.id
    var sportName by SportsTable.sportName
    var mainMuscles by SportsTable.mainMuscles
    var secondaryMuscles by SportsTable.secondaryMuscles

    override fun toString() = sportName
}