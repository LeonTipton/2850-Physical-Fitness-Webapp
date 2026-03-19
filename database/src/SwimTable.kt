package com.physicalfitness

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object SwimTable : IntIdTable() {

    val exName = varchar("exName", MAX_VARCHAR_LEN)
    val intensity = varchar("intensity", MAX_VARCHAR_LEN)
}