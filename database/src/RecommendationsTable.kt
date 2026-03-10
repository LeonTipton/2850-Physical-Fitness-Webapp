package com.physicalfitness

import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.dao.id.CompositeIdTable

object RecommendationsTable : CompositeIdTable() {
    val exId = reference("ex_id", ExercisesTable, ReferenceOption.CASCADE).entityId()
    val sportId = reference("sport_id", SportsTable, ReferenceOption.CASCADE).entityId()
}