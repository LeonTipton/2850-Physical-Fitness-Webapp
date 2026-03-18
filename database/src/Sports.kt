package com.physicalfitness

import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass
import org.jetbrains.exposed.v1.core.dao.id.EntityID

class Sports(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Sports>(SportsTable)

    var sportName by SportsTable.sportName
    var mainMuscles by SportsMusclesTable.muscleName
    var isMain by SportsMusclesTable.main

    override fun toString() = sportName
}