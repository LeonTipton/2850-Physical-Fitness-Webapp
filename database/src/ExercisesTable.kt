package com.physicalfitness

import org.jetbrains.exposed.v1.core.dao.id.IdTable

object ExercisesTable : IdTable<Int>() {
    val exName = varchar("ex_name", MAX_VARCHAR_LEN)
    val exDescription = varchar("ex_description", MAX_VARCHAR_LEN)
    val difficulty = integer("difficulty")
}