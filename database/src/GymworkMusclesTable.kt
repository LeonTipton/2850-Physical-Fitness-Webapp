package com.physicalfitness

import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.Table

object GymworkMusclesTable : Table() {
    val id = reference("id", GymworkTable.id, ReferenceOption.CASCADE)
    val muscleName = varchar("muscle_name", MAX_VARCHAR_LEN)

}