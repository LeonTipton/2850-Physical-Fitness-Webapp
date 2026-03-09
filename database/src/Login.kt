package com.example

import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass
import org.jetbrains.exposed.v1.core.dao.id.EntityID

class Login(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Login>(LoginTable)

    var uid by LoginTable.uid
    var username by LoginTable.username
    var email by LoginTable.email
    var password by LoginTable.password

    override fun toString() = "User(id=$uid, username=$username, email=$email, password=$password)"
}