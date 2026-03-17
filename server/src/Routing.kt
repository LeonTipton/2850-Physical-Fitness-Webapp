package com.physicalfitness

import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import io.ktor.server.application.call
import io.ktor.server.routing.get
import io.ktor.server.response.respondText

fun Application.configureRouting() {
    routing {

         get("/") {
            call.respondText("Home page")
        }

        get("/login") {
            call.respondText("Login page")
        }

        get("/dashboard") {
            call.respondText("Dashboard page")
        }
       
    }
}
