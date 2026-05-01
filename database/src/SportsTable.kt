package com.physicalfitness

import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object SportsTable : IntIdTable() {
    val sportName = varchar("name", MAX_VARCHAR_LEN)
    val mainMuscles = varchar("mainMuscles", MAX_VARCHAR_LEN)
    val secondaryMuscles = varchar("secondaryMuscles", MAX_VARCHAR_LEN)
}