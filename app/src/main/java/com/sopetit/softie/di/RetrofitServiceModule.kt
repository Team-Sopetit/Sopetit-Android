package com.sopetit.softie.di

import com.sopetit.softie.data.service.HappinessRoutineService
import com.sopetit.softie.data.service.MemberService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitServiceModule {
    @Provides
    fun providesMemberService(@RetrofitModule.SoftieType retrofit: Retrofit): MemberService =
        retrofit.create(MemberService::class.java)

    @Provides
    fun providesHappinessRoutineService(@RetrofitModule.SoftieType retrofit: Retrofit): HappinessRoutineService =
        retrofit.create(HappinessRoutineService::class.java)
}
