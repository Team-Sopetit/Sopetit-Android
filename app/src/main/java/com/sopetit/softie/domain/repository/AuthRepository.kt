package com.sopetit.softie.domain.repository

import com.sopetit.softie.domain.entity.Token

interface AuthRepository {
    suspend fun postLogin(socialPlatform: String): Result<Token>
    fun getSignedUp(): Boolean
    fun initToken(accessToken: String, refreshToken: String)
    fun initSignUpState(isSignUpState: Boolean)
}
