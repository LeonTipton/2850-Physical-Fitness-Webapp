package com.physicalfitness

import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.Table

object BiometricsTable : Table() {
    val uid = reference("user_id", UserTable, ReferenceOption.CASCADE) // foreign key from UserTable
    val weight = float("weight")
    val height = float("height")
    val fitness = integer("fitness")
    val targetFitness = float("targetFitness")
}