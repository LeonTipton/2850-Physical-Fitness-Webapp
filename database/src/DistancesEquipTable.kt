package com.physicalfitness

import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.Table

object DistancesEquipTable : Table() {
    val id = reference("id", DistancesTable.id, ReferenceOption.CASCADE)
    val equip = varchar("equip", MAX_VARCHAR_LEN)
}