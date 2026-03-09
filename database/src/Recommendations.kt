package com.physicalfitness

import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass
import org.jetbrains.exposed.v1.core.dao.id.EntityID

class Recommendations(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Recommendations>(RecommendationsTable)

    var exId by RecommendationsTable.exId
    var sportId by RecommendationsTable.sportId
}