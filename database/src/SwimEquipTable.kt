package com.physicalfitness

import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.Table

object SwimEquipTable : Table() {
    val id = reference("id", SwimTable.id, ReferenceOption.CASCADE)
    val equip = varchar("equip", MAX_VARCHAR_LEN)
}