package com.example

import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object LoginTable: IntIdTable() {
    val uid = reference("uid", UserTable.uid, ReferenceOption.CASCADE)
    val username = varchar("username", MAX_VARCHAR_LEN)
    val email = varchar("email", MAX_VARCHAR_LEN)
    val password = varchar("password", MAX_VARCHAR_LEN)
}