package com.physicalfitness

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass

class Distances(id : EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Distances>(DistancesTable)

    var exName by DistancesTable.exName
    var intensity by DistancesIntensityTable.intensity
    var type by DistancesTypeTable.exType
    var equipment by DistancesEquipTable.equip

}