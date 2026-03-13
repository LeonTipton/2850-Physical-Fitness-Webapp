package com.physicalfitness

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object DistancesTable : IntIdTable() {

    val exName = varchar("exName", MAX_VARCHAR_LEN)
    val intensity = varchar("intensity", MAX_VARCHAR_LEN)
    val type = varchar("type", MAX_VARCHAR_LEN)
    val equipment = varchar("equipment", MAX_VARCHAR_LEN)
}