package com.physicalfitness

import java.io.FileReader
import org.apache.commons.csv.CSVFormat
import org.jetbrains.exposed.v1.core.StdOutSqlLogger
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.core.innerJoin
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.insertAndGetId
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.batchInsert


const val GYMWORK_DATA = "csv/gymwork.csv"
const val DISTANCE_DATA = "csv/distance.csv"
const val SPORTS_DATA = "csv/sports.csv"
const val WATERWORK_DATA = "csv/waterwork.csv"

fun main(args: Array<String>) {
    val sqlLogging = args.isNotEmpty() && args[0] == "--sql"

    transaction(PhysicalFitDatabase.db) {
        if (sqlLogging) addLogger(StdOutSqlLogger)

        SchemaUtils.drop(
            BiometricsTable,
            DistancesTable,
            GymworkTable,
            GymworkMusclesTable,
            LoginTable,
            RecommendationsTable,
            SportsTable,
            SportsMusclesTable,
            UserTable,
            WaterworkTable,
        )

        SchemaUtils.create(
            BiometricsTable,
            DistancesTable,
            GymworkTable,
            GymworkMusclesTable,
            LoginTable,
            RecommendationsTable,
            SportsTable,
            SportsMusclesTable,
            UserTable,
            WaterworkTable,
        )

        addDistance(DISTANCE_DATA)
        addWaterwork(WATERWORK_DATA)
        addGymwork(GYMWORK_DATA)
        addSports(SPORTS_DATA)
         createRecommendations()
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

fun addGymwork(filename : String) {
    FileReader(filename).use { reader ->
        val records = CSVFormat.DEFAULT.parse(reader).drop(1)
        for (record in records) {
            val exerciseId = GymworkTable.insertAndGetId {
                it[exName] = record[0]
                it[youtubeLink] = record[2]
            }
            record[1].split(";").forEach { muscle ->
                GymworkMusclesTable.insert {
                    it[id] = exerciseId
                    it[muscleName] = muscle
                }
            }
        }
    }
}

fun addSports(filename : String) {
    FileReader(filename).use { reader ->
        val records = CSVFormat.DEFAULT.parse(reader).drop(1)
        for (record in records) {
            val sportId = SportsTable.insertAndGetId {
                it[sportName] = record[0]
            }
            record[1].split(";").forEach { muscle ->
                SportsMusclesTable.insert {
                    it[id] = sportId
                    it[muscleName] = muscle
                    it[main] = true
                }
            }

            record[2].split(";").forEach { muscle ->
                SportsMusclesTable.insert {
                    it[id] = sportId
                    it[muscleName] = muscle
                    it[main] = false
                }
            }
        }
    }
}

fun createRecommendations() {
    val joinTable = GymworkMusclesTable.innerJoin(
            otherTable = SportsMusclesTable,
            onColumn = { GymworkMusclesTable.muscleName },
            otherColumn = { SportsMusclesTable.muscleName }
        )

    val matcher = joinTable.select(
            GymworkMusclesTable.id,
            SportsMusclesTable.id
        )
        .where { 
            GymworkMusclesTable.muscleName eq SportsMusclesTable.muscleName
        }
        .withDistinct()

    RecommendationsTable.batchInsert(matcher) { row ->
        this[RecommendationsTable.exId] = row[GymworkMusclesTable.id]
        this[RecommendationsTable.sportId] = row[SportsMusclesTable.id]
    }
}
