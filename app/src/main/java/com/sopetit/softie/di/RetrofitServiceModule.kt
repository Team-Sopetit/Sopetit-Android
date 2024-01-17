package com.sopetit.softie.di

import com.sopetit.softie.data.service.AuthService
import com.sopetit.softie.data.service.DailyRoutineService
import com.sopetit.softie.data.service.DollService
import com.sopetit.softie.data.service.HappinessRoutineService
import com.sopetit.softie.data.service.MemberHappinessRoutineService
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
    fun providesAuthService(@RetrofitModule.SoftieType retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    fun providesHappinessRoutineService(@RetrofitModule.SoftieType retrofit: Retrofit): HappinessRoutineService =
        retrofit.create(HappinessRoutineService::class.java)

    @Provides
    fun providesDailyRoutineService(@RetrofitModule.SoftieType retrofit: Retrofit): DailyRoutineService =
        retrofit.create(DailyRoutineService::class.java)

    @Provides
    fun providesDollService(@RetrofitModule.SoftieType retrofit: Retrofit): DollService =
        retrofit.create(DollService::class.java)

    @Provides
    fun providesMemberHappinessRoutineService(@RetrofitModule.SoftieType retrofit: Retrofit): MemberHappinessRoutineService =
        retrofit.create(MemberHappinessRoutineService::class.java)
}
