package com.physicalfitness

import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.Table

object RecommendationsTable : Table() {
    val exId = reference("ex_id", ExercisesTable, ReferenceOption.CASCADE)
    val sportId = reference("sport_id", SportsTable, ReferenceOption.CASCADE)
}