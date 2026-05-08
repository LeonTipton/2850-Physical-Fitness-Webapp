package com.physicalfitness

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldNotBe

class PasswordHashTest : StringSpec({

    "hashPassword does not store the raw password" {
        val rawPassword = "Password123!"
        val hashedPassword = PasswordHash.hashPassword(rawPassword)

        hashedPassword shouldNotBe rawPassword
    }

    "verifyPassword accepts the correct password" {
        val rawPassword = "Password123!"
        val hashedPassword = PasswordHash.hashPassword(rawPassword)

        PasswordHash.verifyPassword(rawPassword, hashedPassword).shouldBeTrue()
    }

    "verifyPassword rejects the wrong password" {
        val hashedPassword = PasswordHash.hashPassword("Password123!")

        PasswordHash.verifyPassword("WrongPassword123!", hashedPassword).shouldBeFalse()
    }
})
