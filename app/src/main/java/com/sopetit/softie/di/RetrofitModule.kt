package com.sopetit.softie.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopetit.softie.BuildConfig
import com.sopetit.softie.data.source.LocalDataSource
import com.sopetit.softie.domain.repository.RefreshTokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private val json = Json { ignoreUnknownKeys = true }
    private const val CONTENT_TYPE = "Content-Type"
    private const val APPLICATION_JSON = "application/json"
    private const val BEARER = "Bearer "
    private const val AUTHORIZATION = "Authorization"
    private const val EXPIRED_TOKEN = 401

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class SoftieType

    @Provides
    @Singleton
    @SoftieType
    fun providesSoftieInterceptor(
        refreshTokenRepository: RefreshTokenRepository,
        localDataSource: LocalDataSource
    ): Interceptor = Interceptor { chain ->
        val request = chain.request()
        var response = chain.proceed(
            request
                .newBuilder()
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .addHeader(AUTHORIZATION, BEARER + localDataSource.accessToken)
                .build()
        )
        when (response.code) {
            EXPIRED_TOKEN -> {
                response.close()
                runBlocking {
                    refreshTokenRepository.postRefreshToken().onSuccess { accessToken ->
                        refreshTokenRepository.setAccessToken(accessToken.accessToken)
                        response = chain.proceed(
                            request
                                .newBuilder()
                                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                                .addHeader(
                                    AUTHORIZATION,
                                    BEARER + localDataSource.accessToken
                                )
                                .build()
                        )
                    }
                }
            }
        }
        response
    }

    @Provides
    @Singleton
    @SoftieType
    fun providesSoftieOkHttpClient(@SoftieType interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level =
                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                }
            ).build()

    @Provides
    @Singleton
    @SoftieType
    fun providesSoftieRetrofit(@SoftieType okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(APPLICATION_JSON.toMediaType()))
            .build()
}
