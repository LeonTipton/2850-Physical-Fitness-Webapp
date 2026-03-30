package com.physicalfitness

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.v1.jdbc.transactions.TransactionManager
import com.physicalfitness.PhysicalFitDatabase

fun Application.module() {
    TransactionManager.defaultDatabase = PhysicalFitDatabase.db
    configureErrorHandling()
    configureRouting()
}

fun main() {
    embeddedServer(
        Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}
