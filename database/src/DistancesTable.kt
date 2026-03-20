package com.physicalfitness

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object DistancesTable : IntIdTable() {
    val exName = varchar("exName", MAX_VARCHAR_LEN)
}