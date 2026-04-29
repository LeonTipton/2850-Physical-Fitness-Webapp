package com.physicalfitness

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass

class Waterwork(id : EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Waterwork>(WaterworkTable)

    var exName by WaterworkTable.exName
    var intensity by WaterworkTable.intensity
    var strokes by WaterworkTable.strokes
    var equipment by WaterworkTable.equipment
}