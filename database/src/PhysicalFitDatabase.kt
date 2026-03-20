package com.physicalfitness

import org.jetbrains.exposed.v1.jdbc.Database

const val MAX_VARCHAR_LEN = 256 // define max length for a varchar

object PhysicalFitDatabase {
    const val URL = "jdbc:h2:./physicalfit" // define connection string
    const val DRIVER = "org.h2.Driver" // define driver

    // connect to the database
    // defined as a lazy delegate to delay initialisation of property
    val db by lazy {
        Database.connect(URL, DRIVER)
    }
}