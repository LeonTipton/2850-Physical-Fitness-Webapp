package com.physicalfitness

import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object RecommendationsTable : IntIdTable() {
    val exId = reference("ex_id", ExercisesTable.id, ReferenceOption.CASCADE)
    val sportId = reference("sport_id", SportsTable.id, ReferenceOption.CASCADE)
}