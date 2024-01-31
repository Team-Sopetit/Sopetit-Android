package com.sopetit.softie.di

import com.sopetit.softie.data.repositoryImpl.AuthRepositoryImpl
import com.sopetit.softie.data.repositoryImpl.DailyRoutineRepositoryImpl
import com.sopetit.softie.data.repositoryImpl.DollRepositoryImpl
import com.sopetit.softie.data.repositoryImpl.HappinessRoutineRepositoryImpl
import com.sopetit.softie.data.repositoryImpl.MemberHappinessRoutineRepositoryImpl
import com.sopetit.softie.data.repositoryImpl.MemberRepositoryImpl
import com.sopetit.softie.data.repositoryImpl.VersionRepositoryImpl
import com.sopetit.softie.domain.repository.AuthRepository
import com.sopetit.softie.domain.repository.DailyRoutineRepository
import com.sopetit.softie.domain.repository.DollRepository
import com.sopetit.softie.domain.repository.HappinessRoutineRepository
import com.sopetit.softie.domain.repository.MemberHappinessRoutineRepository
import com.sopetit.softie.domain.repository.MemberRepository
import com.sopetit.softie.domain.repository.VersionRepository
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
    abstract fun bindToDailyRoutineRepository(
        dailyRoutineRepositoryImpl: DailyRoutineRepositoryImpl
    ): DailyRoutineRepository

    @Binds
    @Singleton
    abstract fun bindToDollRepository(
        dollRepositoryImpl: DollRepositoryImpl
    ): DollRepository

    @Binds
    @Singleton
    abstract fun bindToHappinessRoutineRepository(
        happinessRoutineRepositoryImpl: HappinessRoutineRepositoryImpl
    ): HappinessRoutineRepository

    @Binds
    @Singleton
    abstract fun bindToMemberHappinessRoutineRepository(
        memberHappinessRoutineRepositoryImpl: MemberHappinessRoutineRepositoryImpl
    ): MemberHappinessRoutineRepository

    @Binds
    @Singleton
    abstract fun bindToAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindToVersionRepository(
        versionRepositoryImpl: VersionRepositoryImpl
    ): VersionRepository
}
