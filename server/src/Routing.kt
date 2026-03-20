package com.physicalfitness

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.routing.routing
import io.ktor.server.routing.get
import io.ktor.server.response.respondFile
import java.io.File

fun Application.configureRouting() {
    routing {

        get("/") {
            call.respondFile(File("index.html"))
        }

        get("/login") {
            call.respondFile(File("pages/login_page.html"))
        }

        get("/register") {
            call.respondFile(File("pages/register_page.html"))
        }

    }
}
