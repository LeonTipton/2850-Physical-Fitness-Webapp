package com.physicalfitness

import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object SportsTable : IntIdTable() {
    val uid = reference("user_id", UserTable, ReferenceOption.CASCADE) // foreign key from UserTable
    val sportName = varchar("name", MAX_VARCHAR_LEN)
    val sportDescription = varchar("sport_description", MAX_VARCHAR_LEN)
}