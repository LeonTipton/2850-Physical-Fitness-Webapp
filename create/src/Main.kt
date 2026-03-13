package com.physicalfitness

import org.jetbrains.exposed.v1.core.StdOutSqlLogger
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.io.FileReader
import org.apache.commons.csv.CSVFormat
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.insertAndGetId

const val GYMWORK_DATA = "csv/gymwork.csv"
const val DISTANCE_DATA = "csv/distance.csv"
const val SPORTS_DATA = "csv/sports.csv"
const val WATERWORK_DATA = "csv/waterwork.csv"

typealias NameToIdMap = LinkedHashMap<String, EntityID<Int>>

fun main(args: Array<String>) {
    val sqlLogging = args.isNotEmpty() && args[0] == "--sql"

    transaction(PhysicalFitDatabase.db) {
        if (sqlLogging) addLogger(StdOutSqlLogger)

        SchemaUtils.drop(
            BiometricsTable,
            DistancesTable,
            GymworkTable,
            LoginTable,
            RecommendationsTable,
            SportsTable,
            UserTable,
            WaterworkTable,
        )

        SchemaUtils.drop(
            BiometricsTable,
            DistancesTable,
            GymworkTable,
            LoginTable,
            RecommendationsTable,
            SportsTable,
            UserTable,
            WaterworkTable,
        )

        addDistance(DISTANCE_DATA)
        addWaterwork(WATERWORK_DATA)
        val exercises = addGymwork(GYMWORK_DATA)
        val sports = addSports(SPORTS_DATA)
        createRecommendations(exercises, sports)
    }
}

fun addDistance(filename : String) {
    FileReader(filename).use { reader ->
        val records = CSVFormat.DEFAULT.parse(reader).drop(1)
        for (record in records) {
            DistancesTable.insert {
                it[exName] = record[0]
                it[intensity] = record[1]
                it[type] = record[2]
                it[equipment] = record[3]
            }
        }
    }
}

fun addWaterwork(filename: String) {
    FileReader(filename).use { reader ->
        val records = CSVFormat.DEFAULT.parse(reader).drop(1)
        for (record in records) {
            WaterworkTable.insert {
                it[exName] = record[0]
                it[intensity] = record[1]
                it[strokes] = record[2]
                it[equipment] = record[3]
            }
        }
    }
}

fun addGymwork(filename : String) : NameToIdMap {
    FileReader(filename).use { reader ->
        val records = CSVFormat.DEFAULT.parse(reader).drop(1)
        val exercises  = NameToIdMap()
        for (record in records) {
            exercises[record[0]] = GymworkTable.insertAndGetId {
                it[exName] = record[0]
                it[muscleGroups] = record[1]
                it[youtubeLink] = record[2]
            }
        }
        return exercises
    }
}

fun addSports(filename : String) : NameToIdMap {
    FileReader(filename).use { reader ->
        val records = CSVFormat.DEFAULT.parse(reader).drop(1)
        val sports = NameToIdMap()
        for (record in records) {
            sports[record[0]] = SportsTable.insertAndGetId {
                it[sportName] = record[0]
                it[mainMuscles] = record[1]
                it[secondaryMuscles] = record[2]
            }
        }
        return sports
    }
}

fun createRecommendations(exercises : NameToIdMap, sports : NameToIdMap) {
    RecommendationsTable.insert {
        it[exId]
        it[sportId]
//        need to filter by comparing gymwork muscle groups and sport muscle groups
    }
}
