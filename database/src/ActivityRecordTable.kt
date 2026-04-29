package com.physicalfitness

import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object ActivityRecordTable : IntIdTable() {
    val userId = reference("user_id", UserTable, onDelete = ReferenceOption.CASCADE)

    val activityType = varchar("activity_type", MAX_VARCHAR_LEN)
    val activityName = varchar("activity_name", MAX_VARCHAR_LEN)

    val durationMinutes = integer("duration_minutes").nullable()
    val distanceKm = double("distance_km").nullable()

    val notes = varchar("notes", MAX_VARCHAR_LEN).nullable()

    val createdAt = varchar("created_at", MAX_VARCHAR_LEN)
}