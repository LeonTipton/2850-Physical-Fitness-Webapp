package com.physicalfitness

import io.ktor.server.application.Application
import org.jetbrains.exposed.v1.jdbc.transactions.TransactionManager
import com.physicalfitness.PhysicalFitDatabase

fun Application.module() {
    TransactionManager.defaultDatabase = PhysicalFitDatabase.db
    // configureErrorHandling()
    // configureTemplates
    // configureRouting()
}

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}