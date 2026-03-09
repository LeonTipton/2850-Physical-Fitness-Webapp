package com.example

import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass
import org.jetbrains.exposed.v1.core.dao.id.EntityID

class Biometrics(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Biometrics>(BiometricsTable)

    var uid by BiometricsTable.uid
    var weight by BiometricsTable.weight
    var height by BiometricsTable.height
    var fitness by BiometricsTable.fitness
    var targetFitness by BiometricsTable.targetFitness

}