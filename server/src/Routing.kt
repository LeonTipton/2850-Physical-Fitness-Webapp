package com.physicalfitness

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.http.content.staticFiles
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respondFile
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import java.io.File
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.jetbrains.exposed.v1.core.eq
import io.ktor.server.response.respond
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.selectAll

fun Application.configureRouting() {
    routing {
        staticFiles("/pages", File("server/src/frontend/front-end/pages"))
        staticFiles("/logo", File("server/src/frontend/front-end/logo"))
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

        post("/api/login") {
            val params = call.receiveParameters()
            val email = params["email"] ?: ""
            val password = params["password"] ?: ""

            if (email.isBlank() || password.isBlank()) {
                call.respondText(
                    """{"ok":false,"message":"Email and password are required"}""",
                    ContentType.Application.Json,
                    HttpStatusCode.BadRequest
                )
                return@post
            }

            val loginEntry = transaction {
                Login.find(LoginTable.email eq email).firstOrNull()
            }

            if (loginEntry == null) {
                call.respondText(
                    """{"ok":false,"message":"Invalid email or password"}""",
                    ContentType.Application.Json,
                    HttpStatusCode.Unauthorized
                )
                return@post
            }

            if (!PasswordHash.verifyPassword(password, loginEntry.password)) {
                call.respondText(
                    """{"ok":false,"message":"Invalid email or password"}""",
                    ContentType.Application.Json,
                    HttpStatusCode.Unauthorized
                )
                return@post
            }

            val userId = loginEntry.id.value

            call.respondText(
                """{"ok":true,"user":{"id":$userId,"username":"${loginEntry.username}","email":"${loginEntry.email}"}}""",
                ContentType.Application.Json
            )
        }

        post("/api/register") {
            val params = call.receiveParameters()
            val username = params["username"] ?: ""
            val email = params["email"] ?: ""
            val password = params["password"] ?: ""

            if (username.isBlank() || email.isBlank() || password.isBlank()) {
                call.respondText(
                    """{"ok":false,"message":"All fields are required"}""",
                    ContentType.Application.Json,
                    HttpStatusCode.BadRequest
                )
                return@post
            }

            val existing = transaction {
                Login.find(LoginTable.email eq email).firstOrNull()
            }
            if (existing != null) {
                call.respondText(
                    """{"ok":false,"message":"Email already registered"}""",
                    ContentType.Application.Json,
                    HttpStatusCode.Conflict
                )
                return@post
            }

            val hashed = PasswordHash.hashPassword(password)

            transaction {
                val newUser = User.new {
                    name = username
                    notificationOption = true
                }
                Login.new(newUser.id.value) {
                    this.username = username
                    this.email = email
                    this.password = hashed
                }
            }

            call.respondText(
                """{"ok":true,"message":"Account created"}""",
                ContentType.Application.Json,
                HttpStatusCode.Created
            )
        }

        get("/api/user") {
            val uid = call.request.queryParameters["uid"]?.toIntOrNull()
            if (uid == null) {
                call.respondText(
                    """{"ok":false,"message":"Missing uid parameter"}""",
                    ContentType.Application.Json,
                    HttpStatusCode.BadRequest
                )
                return@get
            }

            val loginEntry = transaction { Login.findById(uid) }
            val user = transaction { User.findById(uid) }

            if (loginEntry == null || user == null) {
                call.respondText(
                    """{"ok":false,"message":"User not found"}""",
                    ContentType.Application.Json,
                    HttpStatusCode.NotFound
                )
                return@get
            }

            call.respondText(
                """{"ok":true,"user":{"id":$uid,"username":"${loginEntry.username}","email":"${loginEntry.email}","name":"${user.name}"}}""",
                ContentType.Application.Json
            )
        }

        post("/api/user/delete") {
            val params = call.receiveParameters()
            val uid = params["uid"]?.toIntOrNull()
            if (uid == null) {
                call.respondText(
                    """{"ok":false,"message":"Missing uid"}""",
                    ContentType.Application.Json,
                    HttpStatusCode.BadRequest
                )
                return@post
            }

            val user = transaction { User.findById(uid) }
            if (user == null) {
                call.respondText(
                    """{"ok":false,"message":"User not found"}""",
                    ContentType.Application.Json,
                    HttpStatusCode.NotFound
                )
                return@post
            }

            transaction { user.delete() }

            call.respondText(
                """{"ok":true,"message":"Account deleted"}""",
                ContentType.Application.Json
            )
        }

        post("/api/user/password") {
            val params = call.receiveParameters()
            val uid = params["uid"]?.toIntOrNull()
            val oldPassword = params["oldPassword"] ?: ""
            val newPassword = params["newPassword"] ?: ""

            if (uid == null || oldPassword.isBlank() || newPassword.isBlank()) {
                call.respondText(
                    """{"ok":false,"message":"All fields are required"}""",
                    ContentType.Application.Json,
                    HttpStatusCode.BadRequest
                )
                return@post
            }

            val loginEntry = transaction { Login.findById(uid) }
            if (loginEntry == null) {
                call.respondText(
                    """{"ok":false,"message":"User not found"}""",
                    ContentType.Application.Json,
                    HttpStatusCode.NotFound
                )
                return@post
            }

            if (!PasswordHash.verifyPassword(oldPassword, loginEntry.password)) {
                call.respondText(
                    """{"ok":false,"message":"Current password is incorrect"}""",
                    ContentType.Application.Json,
                    HttpStatusCode.Unauthorized
                )
                return@post
            }

            transaction {
                loginEntry.password = PasswordHash.hashPassword(newPassword)
            }

            call.respondText(
                """{"ok":true,"message":"Password updated"}""",
                ContentType.Application.Json
            )
        }
        get("/api/sports") {
            val data = transaction {
                Sports.all().map {
                    mapOf(
                        "id" to it.id.value,
                        "name" to it.sportName
                    )
                }
            }
            call.respondText(data.toString())
        }

        get("/api/gym") {
            val data = transaction {
                Gymwork.all().map {
                    mapOf(
                        "id" to it.id.value,
                        "name" to it.exName,
                        "muscleGroups" to it.muscleGroups,
                        "youtubeLink" to it.youtubeLink
                    )
                }
            }
            call.respondText(data.toString())
        }

        get("/api/swim") {
            val data = transaction {
                Waterwork.all().map {
                    mapOf(
                        "id" to it.id.value,
                        "name" to it.exName,
                        "intensity" to it.intensity,
                        "strokes" to it.strokes,
                        "equipment" to it.equipment
                    )
                }
            }
            call.respondText(data.toString())
        }

        get("/api/distances") {
            val data = transaction {
                Distances.all().map {
                    mapOf(
                        "id" to it.id.value,
                        "name" to it.exName,
                        "intensity" to it.intensity,
                        "type" to it.type,
                        "equipment" to it.equipment
                    )
                }
            }
            call.respondText(data.toString())
        }

        post("/api/records") {
            val params = call.receiveParameters()

            val uid = params["uid"]?.toIntOrNull()
            val activityType = params["activityType"] ?: ""
            val activityName = params["activityName"] ?: ""
            val durationMinutes = params["durationMinutes"]?.toIntOrNull()
            val distanceKm = params["distanceKm"]?.toDoubleOrNull()
            val notes = params["notes"] ?: ""

            if (uid == null || activityType.isBlank() || activityName.isBlank()) {
                call.respondText(
                    """{"ok":false,"message":"Missing required fields"}""",
                    ContentType.Application.Json,
                    HttpStatusCode.BadRequest
                )
                return@post
            }

        transaction {
            ActivityRecordTable.insert {
                it[ActivityRecordTable.userId] = uid
                it[ActivityRecordTable.activityType] = activityType
                it[ActivityRecordTable.activityName] = activityName
                it[ActivityRecordTable.durationMinutes] = durationMinutes
                it[ActivityRecordTable.distanceKm] = distanceKm
                it[ActivityRecordTable.notes] = notes
                it[ActivityRecordTable.createdAt] = System.currentTimeMillis().toString()
            }
        }
            call.respondText(
                """{"ok":true,"message":"Record added"}""",
                ContentType.Application.Json
            )
        }

        get("/api/test") {
            call.respondText("test works")
        }

        get("/api/records") {
            val uid = call.request.queryParameters["uid"]?.toIntOrNull()

            if (uid == null) {
                call.respondText(
                    """{"ok":false,"message":"Missing uid"}""",
                    ContentType.Application.Json,
                    HttpStatusCode.BadRequest
                )
                return@get
            }

            val data = transaction {
                ActivityRecordTable.selectAll()
                    .where { ActivityRecordTable.userId eq uid }
                    .map {
                        mapOf(
                            "id" to it[ActivityRecordTable.id].value,
                            "activityType" to it[ActivityRecordTable.activityType],
                            "activityName" to it[ActivityRecordTable.activityName],
                            "durationMinutes" to it[ActivityRecordTable.durationMinutes],
                            "distanceKm" to it[ActivityRecordTable.distanceKm],
                            "notes" to it[ActivityRecordTable.notes],
                            "createdAt" to it[ActivityRecordTable.createdAt]
                        )
                    }
            }

            val json = data.joinToString(prefix = "[", postfix = "]") { r ->
                """{"id":${r["id"]},"activityType":"${r["activityType"]}","activityName":"${r["activityName"]}","durationMinutes":${r["durationMinutes"] ?: 0},"distanceKm":${r["distanceKm"] ?: 0},"notes":"${r["notes"] ?: ""}","createdAt":"${r["createdAt"]}"}"""
            }

            call.respondText(json, ContentType.Application.Json)
        }
    }
}   