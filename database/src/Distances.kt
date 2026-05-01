package com.physicalfitness

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass

class Distances(id : EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Distances>(DistancesTable)

    var exName by DistancesTable.exName
    var intensity by DistancesTable.intensity
    var type by DistancesTable.type
    var equipment by DistancesTable.equipment

}