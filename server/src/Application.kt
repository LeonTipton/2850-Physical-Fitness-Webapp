package com.example

import io.ktor.server.application.Application
import org.jetbrains.exposed.v1.jdbc.transactions.TransactionManager

fun Application.module() {
    TransactionManager.defaultDatabase = User.db
}