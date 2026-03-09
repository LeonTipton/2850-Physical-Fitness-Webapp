package com.physicalfitness

import org.jetbrains.exposed.v1.jdbc.Database

object PhysicalFitDatabase {
    const val URL = "jdbc:h2:./physicalfit"
    const val DRIVER = "org.h2.Driver"

    val db by lazy {
        Database.connect(URL, DRIVER)
    }
}