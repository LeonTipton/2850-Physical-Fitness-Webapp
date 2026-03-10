package com.physicalfitness

import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object BiometricsTable : IntIdTable() {
    override val id = reference("user_id", UserTable.id, ReferenceOption.CASCADE) // foreign key from UserTable
    val weight = float("weight")
    val height = float("height")
    val fitness = integer("fitness")
    val targetFitness = float("targetFitness")
}