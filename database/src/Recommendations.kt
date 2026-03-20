package com.physicalfitness

import org.jetbrains.exposed.v1.core.dao.id.CompositeID
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.CompositeEntity
import org.jetbrains.exposed.v1.dao.CompositeEntityClass

class Recommendations(id: EntityID<CompositeID>) : CompositeEntity(id) {
    companion object : CompositeEntityClass<Recommendations>(RecommendationsTable)

    var exId by RecommendationsTable.exId
    var sportId by RecommendationsTable.sportId
}