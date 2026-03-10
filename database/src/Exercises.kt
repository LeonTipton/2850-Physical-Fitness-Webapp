package com.physicalfitness

import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass
import org.jetbrains.exposed.v1.core.dao.id.EntityID

class Exercises(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Exercises>(ExercisesTable)

    var exID by ExercisesTable.id
    var exName by ExercisesTable.exName
    var exDescription by ExercisesTable.exDescription
    var difficulty by ExercisesTable.difficulty

    override fun toString() = exName
}