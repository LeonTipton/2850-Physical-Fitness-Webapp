package com.physicalfitness

import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object BiometricsTable : IntIdTable() {
    val uid = reference("uid", UserTable.uid, ReferenceOption.CASCADE)
    val weight = float("weight")
    val height = float("height")
    val fitness = integer("fitness")
    val targetFitness = float("targetFitness")
}