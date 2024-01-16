package com.sopetit.softie.domain.repository

interface DollRepository {

    suspend fun getDollType(type: String): Result<String>
}
