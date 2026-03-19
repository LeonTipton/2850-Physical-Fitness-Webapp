package com.physicalfitness

import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.Table

object SwimStrokesTable : Table() {
    val id = reference("id", SwimTable.id, ReferenceOption.CASCADE)
    val stroke = varchar("stroke", MAX_VARCHAR_LEN)
}