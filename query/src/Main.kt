package com.physicalfitness

import org.jetbrains.exposed.v1.core.StdOutSqlLogger

import org.jetbrains.exposed.v1.jdbc.transactions.transaction

fun main(args: Array<String>) {
    val sqlLogging = args.isNotEmpty() && args[0] == "--sql"

    transaction(PhysicalFitDatabase.db) {
        if (sqlLogging) addLogger(StdOutSqlLogger)

    }
}