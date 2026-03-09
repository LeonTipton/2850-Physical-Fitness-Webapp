package com.physicalfitness

import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object SportsTable : IntIdTable() {
    val uid = reference("uid", UserTable.uid, ReferenceOption.CASCADE)
    val sportId = integer("sport_id").autoIncrement()
    val sportName = varchar("name", MAX_VARCHAR_LEN)
    val sportDescription = varchar("sport_description", MAX_VARCHAR_LEN)
}