package com.physicalfitness

import org.jetbrains.exposed.v1.core.StdOutSqlLogger

import org.jetbrains.exposed.v1.jdbc.transactions.transaction

fun main(args: Array<String>) {
    val sqlLogging = args.isNotEmpty() && args[0] == "--sql"

    transaction(PhysicalFitDatabase.db) {
        if (sqlLogging) addLogger(StdOutSqlLogger)

        // add give recommendations code here
        // if sport given, search sport table for that id and match that sportId to all gymworkIds
        // if muscle group given, search gymworkMuscle table for gymwork ids and output names from gymwork table
    }
}