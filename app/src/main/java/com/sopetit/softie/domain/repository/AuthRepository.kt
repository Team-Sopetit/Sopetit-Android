package com.sopetit.softie.domain.repository

interface AuthRepository {

    suspend fun deleteAuth(): Result<Unit>
}
