package com.physicalfitness

import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass
import org.jetbrains.exposed.v1.core.dao.id.EntityID

class User(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<User>(UserTable)

    var uid by UserTable.id
    var name by UserTable.name
    var notificationOption by UserTable.notificationOptIn

    override fun toString() = "User(id=$uid, name=$name, notificationOption=$notificationOption)"
}