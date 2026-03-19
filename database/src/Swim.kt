package com.physicalfitness

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass

class Swim(id : EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Swim>(SwimTable)

    var exName by SwimTable.exName
    var intensity by SwimTable.intensity
    var stroke by SwimStrokesTable.stroke
    var equipment by SwimEquipTable.equip
}