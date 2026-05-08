package com.physicalfitness

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object WaterworkTable : IntIdTable() {

    val exName = varchar("exName", MAX_VARCHAR_LEN)
    val intensity = varchar("intensity", MAX_VARCHAR_LEN)
    val strokes = varchar("strokes", MAX_VARCHAR_LEN)
    val equipment = varchar("equipment", MAX_VARCHAR_LEN)
}