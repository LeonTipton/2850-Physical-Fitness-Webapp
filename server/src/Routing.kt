package com.physicalfitness

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.http.content.staticFiles
import io.ktor.server.response.respondFile
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import java.io.File

fun Application.configureRouting() {
    routing {
        staticFiles("/", File("server/src/frontend/front-end"))

        staticFiles("/front-end", File("server/src/frontend/front-end"))

        get("/") {
            call.respondFile(File("server/src/frontend/front-end/index.html"))
        }

        get("/login") {
            call.respondFile(File("server/src/frontend/front-end/pages/login_page.html"))
        }

        get("/register") {
            call.respondFile(File("server/src/frontend/front-end/pages/register_page.html"))
        }
    }
}
