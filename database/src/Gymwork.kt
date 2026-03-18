package com.physicalfitness

import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass
import org.jetbrains.exposed.v1.core.dao.id.EntityID


class Gymwork(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Gymwork>(GymworkTable)

    var exName by GymworkTable.exName
    var muscleName by GymworkMusclesTable.muscleName
    var youtubeLink by GymworkTable.youtubeLink

    override fun toString() = exName
}