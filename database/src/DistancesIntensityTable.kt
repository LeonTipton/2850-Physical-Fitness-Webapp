package com.physicalfitness

import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.Table

object DistancesIntensityTable : Table() {
    val id = reference("id", DistancesTable.id, ReferenceOption.CASCADE)
    val intensity = varchar("intensity", MAX_VARCHAR_LEN)
}