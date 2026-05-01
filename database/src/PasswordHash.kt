package com.physicalfitness

import de.mkammerer.argon2.Argon2Factory

object PasswordHash {

    private val argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id)

    private const val ITERATIONS = 3
    private const val MEMORY = 65536
    private const val PARALLELISM = 1

    fun hashPassword(password: String): String {
        val chars = password.toCharArray()
        return try {
            argon2.hash(ITERATIONS, MEMORY, PARALLELISM, chars)
        } finally {
            argon2.wipeArray(chars)
        }
    }

    fun verifyPassword(password: String, hash: String): Boolean {
        val chars = password.toCharArray()
        return try {
            argon2.verify(hash, chars)
        } finally {
            argon2.wipeArray(chars)
        }
    }
}