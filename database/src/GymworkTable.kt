package com.physicalfitness

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object GymworkTable : IntIdTable() {
    val exName = varchar("ex_name", MAX_VARCHAR_LEN)
    val muscleGroups = varchar("muscle_groups", MAX_VARCHAR_LEN)
    val youtubeLink = varchar("youtube_link", MAX_VARCHAR_LEN)
}