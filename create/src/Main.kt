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
const val SWIM_DATA = "csv/waterwork.csv"

fun main(args: Array<String>) {
    val sqlLogging = args.isNotEmpty() && args[0] == "--sql" // check cmdline args for sql output

    transaction(PhysicalFitDatabase.db) {
        if (sqlLogging) addLogger(StdOutSqlLogger) // output to cmdline

        // drop all tables
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

        // rebuild all tables
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

        // populate all tables
        addDistance(DISTANCE_DATA)
        addSwimming(SWIM_DATA)
        addGymwork(GYMWORK_DATA)
        addSports(SPORTS_DATA)
        createRecommendations()
    }
}

fun addDistance(filename : String) {
    // read the csv file using correct reader
    FileReader(filename).use { reader ->
        val records = CSVFormat.DEFAULT.parse(reader).drop(1)
        // loop over each line
        for (record in records) {
            // add exercise names to the general table
            val distId = DistancesTable.insertAndGetId { // store id for connected tables
                it[exName] = record[0]
            }
            // add intensities to Intensity table
            record[1].split(";").forEach { intensityVal ->
                DistancesIntensityTable.insert {
                    it[id] = distId
                    it[intensity] = intensityVal
                }
            }
            // add exercise types to Type table
            record[2].split(";").forEach { type ->
                DistancesTypeTable.insert {
                    it[id] = distId
                    it[exType] = type
                }
            }
            // add equipment required/recommended to Equip table
            record[3].split(";").forEach { equipment ->
                DistancesEquipTable.insert {
                    it[id] = distId
                    it[equip] = equipment
                }
            }
        }
    }
}

// acts same as above for respective tables
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

// acts same as above for respective tables
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

// acts same as above for respective tables
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
    // build a join table connecting muscle groups from gymwork to sports
    // this has the respective table ids as nonprimary key entities
    val joinTable = GymworkMusclesTable.innerJoin(
            otherTable = SportsMusclesTable,
            onColumn = { GymworkMusclesTable.muscleName },
            otherColumn = { SportsMusclesTable.muscleName }
        )

    // using the join table create a matcher to the respective ids
    // Filter where the muscle names are the same, ensuring the combination
    // of ids is unique
    // (i.e., the composite key 11 will only appear once connecting gymwork id 1 to sport id 1)
    val matcher = joinTable.select(
            GymworkMusclesTable.id,
            SportsMusclesTable.id
        )
        .where { 
            GymworkMusclesTable.muscleName eq SportsMusclesTable.muscleName
        }
        .withDistinct()

    // since no iteration is required to extract data simply batch insert ids into the Recommendations table
    RecommendationsTable.batchInsert(matcher) { row ->
        this[RecommendationsTable.exId] = row[GymworkMusclesTable.id]
        this[RecommendationsTable.sportId] = row[SportsMusclesTable.id]
    }
}
