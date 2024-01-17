package com.sopetit.softie.di

import com.sopetit.softie.data.service.AuthService
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
}
