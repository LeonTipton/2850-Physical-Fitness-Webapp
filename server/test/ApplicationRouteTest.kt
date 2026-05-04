package com.physicalfitness

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.ktor.client.request.get
import io.ktor.client.request.forms.submitForm
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.parameters
import io.ktor.server.testing.testApplication

class ApplicationRouteTest : StringSpec({

    "home page returns OK and contains HTML" {
        testApplication {
            application { module() }

            val response = client.get("/")
            val body = response.bodyAsText()

            response.status shouldBe HttpStatusCode.OK
            body.lowercase() shouldContain "html"
        }
    }

    "login page returns OK" {
        testApplication {
            application { module() }

            val response = client.get("/login")

            response.status shouldBe HttpStatusCode.OK
        }
    }

    "register page returns OK" {
        testApplication {
            application { module() }

            val response = client.get("/register")

            response.status shouldBe HttpStatusCode.OK
        }
    }

    "register rejects missing fields" {
        testApplication {
            application { module() }

            val response = client.submitForm(
                url = "/api/register",
                formParameters = parameters {
                    append("username", "")
                    append("email", "")
                    append("password", "")
                }
            )
            val body = response.bodyAsText()

            response.status shouldBe HttpStatusCode.BadRequest
            body shouldContain "All fields are required"
        }
    }

    "login rejects missing fields" {
        testApplication {
            application { module() }

            val response = client.submitForm(
                url = "/api/login",
                formParameters = parameters {
                    append("email", "")
                    append("password", "")
                }
            )
            val body = response.bodyAsText()

            response.status shouldBe HttpStatusCode.BadRequest
            body shouldContain "Email and password are required"
        }
    }

    "login rejects an unknown account" {
        testApplication {
            application { module() }

            val response = client.submitForm(
                url = "/api/login",
                formParameters = parameters {
                    append("email", "missing-${System.nanoTime()}@example.com")
                    append("password", "password123")
                }
            )
            val body = response.bodyAsText()

            response.status shouldBe HttpStatusCode.Unauthorized
            body shouldContain "Invalid email or password"
        }
    }

    "register login get user change password and delete account journey works" {
        testApplication {
            application { module() }

            val unique = System.nanoTime()
            val username = "TestUser$unique"
            val email = "test-$unique@example.com"
            val password = "Password123!"
            val newPassword = "NewPassword123!"

            val registerResponse = client.submitForm(
                url = "/api/register",
                formParameters = parameters {
                    append("username", username)
                    append("email", email)
                    append("password", password)
                }
            )
            val registerBody = registerResponse.bodyAsText()
            registerResponse.status shouldBe HttpStatusCode.Created
            registerBody shouldContain "Account created"

            val duplicateResponse = client.submitForm(
                url = "/api/register",
                formParameters = parameters {
                    append("username", username)
                    append("email", email)
                    append("password", password)
                }
            )
            duplicateResponse.status shouldBe HttpStatusCode.Conflict

            val loginResponse = client.submitForm(
                url = "/api/login",
                formParameters = parameters {
                    append("email", email)
                    append("password", password)
                }
            )
            val loginBody = loginResponse.bodyAsText()
            loginResponse.status shouldBe HttpStatusCode.OK
            loginBody shouldContain "\"ok\":true"
            loginBody shouldContain email

            val userId = extractUserId(loginBody)
            userId.shouldNotBeNull()

            val getUserResponse = client.get("/api/user?uid=$userId")
            val getUserBody = getUserResponse.bodyAsText()
            getUserResponse.status shouldBe HttpStatusCode.OK
            getUserBody shouldContain username
            getUserBody shouldContain email

            val changePasswordResponse = client.submitForm(
                url = "/api/user/password",
                formParameters = parameters {
                    append("uid", userId.toString())
                    append("oldPassword", password)
                    append("newPassword", newPassword)
                }
            )
            changePasswordResponse.status shouldBe HttpStatusCode.OK

            val oldPasswordLoginResponse = client.submitForm(
                url = "/api/login",
                formParameters = parameters {
                    append("email", email)
                    append("password", password)
                }
            )
            oldPasswordLoginResponse.status shouldBe HttpStatusCode.Unauthorized

            val newPasswordLoginResponse = client.submitForm(
                url = "/api/login",
                formParameters = parameters {
                    append("email", email)
                    append("password", newPassword)
                }
            )
            newPasswordLoginResponse.status shouldBe HttpStatusCode.OK

            val deleteResponse = client.submitForm(
                url = "/api/user/delete",
                formParameters = parameters {
                    append("uid", userId.toString())
                }
            )
            deleteResponse.status shouldBe HttpStatusCode.OK

            val afterDeleteResponse = client.get("/api/user?uid=$userId")
            afterDeleteResponse.status shouldBe HttpStatusCode.NotFound
        }
    }

    "get user rejects missing uid" {
        testApplication {
            application { module() }

            val response = client.get("/api/user")
            val body = response.bodyAsText()

            response.status shouldBe HttpStatusCode.BadRequest
            body shouldContain "Missing uid parameter"
        }
    }

    "delete user rejects missing uid" {
        testApplication {
            application { module() }

            val response = client.submitForm(
                url = "/api/user/delete",
                formParameters = parameters { }
            )
            val body = response.bodyAsText()

            response.status shouldBe HttpStatusCode.BadRequest
            body shouldContain "Missing uid"
        }
    }
})

private fun extractUserId(json: String): Int? {
    return Regex(""""id":(\d+)""")
        .find(json)
        ?.groupValues
        ?.get(1)
        ?.toInt()
}
