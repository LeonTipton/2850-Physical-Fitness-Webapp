package com.physicalfitness

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.pebble.respondTemplate
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respondText

fun Application.configureErrorHandling() {
    install(StatusPages) {
        status(HttpStatusCode.NotFound) { call, _ ->
            call.respondTemplate("needs page example") //HttpStatusCode.NotFound)
        }
        exception<Throwable> { call, cause ->
            call.respondText("500: ${cause.message}")
    
        }
    }
}
