package com.physicalfitness

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

const val MAX_VARCHAR_LEN = 256

object UserTable : IntIdTable() {
    val name = varchar("name", MAX_VARCHAR_LEN)
    val notificationOptIn = bool("notification_opt_in")
}