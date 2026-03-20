package com.physicalfitness

import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.dao.id.IdTable

object LoginTable: IdTable<Int>() {
    override val id = reference("user_id", UserTable, ReferenceOption.CASCADE) // foreign key from UserTable
    val username = varchar("username", MAX_VARCHAR_LEN)
    val email = varchar("email", MAX_VARCHAR_LEN)
    val password = varchar("password", MAX_VARCHAR_LEN)
}