package com.physicalfitness

import com.physicalfitness.DistancesTypeTable.exType
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
const val SWIM_DATA = "csv/waterwork.csv"

fun main(args: Array<String>) {
    val sqlLogging = args.isNotEmpty() && args[0] == "--sql"

    transaction(PhysicalFitDatabase.db) {
        if (sqlLogging) addLogger(StdOutSqlLogger)

        SchemaUtils.drop(
            BiometricsTable,
            DistancesEquipTable,
            DistancesIntensityTable,
            DistancesTable,
            DistancesTypeTable,
            GymworkTable,
            GymworkMusclesTable,
            LoginTable,
            RecommendationsTable,
            SportsTable,
            SportsMusclesTable,
            SwimStrokesTable,
            SwimEquipTable,
            SwimTable,
            UserTable,
        )

        SchemaUtils.create(
            BiometricsTable,
            DistancesEquipTable,
            DistancesIntensityTable,
            DistancesTable,
            DistancesTypeTable,
            GymworkTable,
            GymworkMusclesTable,
            LoginTable,
            RecommendationsTable,
            SportsTable,
            SportsMusclesTable,
            SwimStrokesTable,
            SwimEquipTable,
            SwimTable,
            UserTable,
        )

        addDistance(DISTANCE_DATA)
        addSwimming(SWIM_DATA)
        addGymwork(GYMWORK_DATA)
        addSports(SPORTS_DATA)
         createRecommendations()
    }
}

fun addDistance(filename : String) {
    FileReader(filename).use { reader ->
        val records = CSVFormat.DEFAULT.parse(reader).drop(1)
        for (record in records) {
            val distId = DistancesTable.insertAndGetId {
                it[exName] = record[0]
            }
            record[1].split(";").forEach { intensityVal ->
                DistancesIntensityTable.insert {
                    it[id] = distId
                    it[intensity] = intensityVal
                }
            }
            record[2].split(";").forEach { type ->
                DistancesTypeTable.insert {
                    it[id] = distId
                    it[exType] = type
                }
            }
            record[3].split(";").forEach { equipment ->
                DistancesEquipTable.insert {
                    it[id] = distId
                    it[equip] = equipment
                }
            }
        }
    }
}

fun addSwimming(filename: String) {
    FileReader(filename).use { reader ->
        val records = CSVFormat.DEFAULT.parse(reader).drop(1)
        for (record in records) {
            val swimId = SwimTable.insertAndGetId {
                it[exName] = record[0]
                it[intensity] = record[1]
            }
            record[2].split(";").forEach { strokeAbbr ->
                SwimStrokesTable.insert {
                    it[id] = swimId
                    it[stroke] = strokeAbbr
                }
            }
            record[3].split(";").forEach { equipment ->
                SwimEquipTable.insert {
                    it[id] = swimId
                    it[equip] = equipment
                }
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
