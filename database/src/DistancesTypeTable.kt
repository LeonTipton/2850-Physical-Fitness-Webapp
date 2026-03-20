package com.physicalfitness

import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.Table

object DistancesTypeTable : Table() {
    val id = reference("id", DistancesTable.id, ReferenceOption.CASCADE)
    val exType = varchar("exType", MAX_VARCHAR_LEN)
}