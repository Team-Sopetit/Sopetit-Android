package com.sopetit.softie.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopetit.softie.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
object VersionModule {
    private val json = Json { ignoreUnknownKeys = true }
    private const val CONTENT_TYPE = "Content-Type"
    private const val APPLICATION_JSON = "application/json"

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class VersionType

    @Provides
    @Singleton
    @VersionType
    fun providesVersionInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request()
        var response = chain.proceed(
            request
                .newBuilder()
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .build()
        )
        response
    }

    @Provides
    @Singleton
    @VersionType
    fun providesVersionOkHttpClient(@VersionType interceptor: Interceptor): OkHttpClient =
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
    @VersionType
    fun providesVersionRetrofit(@VersionType okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(APPLICATION_JSON.toMediaType()))
            .build()
}
