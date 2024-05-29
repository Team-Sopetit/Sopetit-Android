package com.sopetit.softie.data.repositoryImpl

import com.sopetit.softie.data.source.AuthDataSource
import com.sopetit.softie.data.source.LocalDataSource
import com.sopetit.softie.domain.entity.Token
import com.sopetit.softie.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val localDataSource: LocalDataSource
) : AuthRepository {
    override suspend fun deleteAuth(): Result<Unit> = runCatching { authDataSource.deleteAuth() }

    override suspend fun postLogin(socialPlatform: String): Result<Token> =
        runCatching { authDataSource.postLogin(socialPlatform) }.map { response ->
            requireNotNull(response.data).toToken()
        }

    override fun initToken(
        accessToken: String,
        refreshToken: String,
        isMemberDollExist: Boolean,
        isSignedUp: Boolean
    ) {
        localDataSource.accessToken = accessToken
        localDataSource.refreshToken = refreshToken
        localDataSource.isMemberDollExist = isMemberDollExist
        localDataSource.isUserSignUp = isSignedUp
    }

    override fun initSignUpState(isSignUpState: Boolean) {
        localDataSource.isUserSignUp = isSignUpState
    }

    override fun getBearType(): String = localDataSource.bearType
    override fun setBearType(bearType: String) {
        localDataSource.bearType = bearType
    }

    override fun getTutorial(): Boolean = localDataSource.isHomeTutorial

    override fun setTutorial(isTutorial: Boolean) {
        localDataSource.isHomeTutorial = isTutorial
    }

    override fun getSignedUp(): Boolean = localDataSource.isUserSignUp
    override fun getMember(): Boolean = localDataSource.isMemberDollExist

    override suspend fun logOut(): Result<Unit> = runCatching {
        authDataSource.logOut()
    }
}
