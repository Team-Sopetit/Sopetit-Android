package com.sopetit.softie.domain.repository

import com.sopetit.softie.domain.entity.Token

interface AuthRepository {

    suspend fun deleteAuth(): Result<Unit>
    suspend fun postLogin(socialPlatform: String): Result<Token>
    fun getSignedUp(): Boolean
    fun getMember(): Boolean
    fun initToken(
        accessToken: String,
        refreshToken: String,
        isMemberDollExist: Boolean,
        isSignedUp: Boolean
    )

    fun initSignUpState(isSignUpState: Boolean)
    suspend fun logOut(): Result<Unit>
    fun getBearType(): String
    fun setBearType(bearType: String)
    fun getTutorial(): Boolean
    fun setTutorial(isTutorial: Boolean)
}
