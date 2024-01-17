package com.sopetit.softie.di

import com.sopetit.softie.data.repositoryImpl.AuthRepositoryImpl
import com.sopetit.softie.data.repositoryImpl.MemberRepositoryImpl
import com.sopetit.softie.domain.repository.AuthRepository
import com.sopetit.softie.domain.repository.MemberRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindToMemberRepository(
        memberRepositoryImpl: MemberRepositoryImpl
    ): MemberRepository

    @Binds
    @Singleton
    abstract fun bindToAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}
