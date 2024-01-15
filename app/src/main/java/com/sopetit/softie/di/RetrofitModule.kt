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
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private val json = Json { ignoreUnknownKeys = true }
    private const val BASE_URL = BuildConfig.BASE_URL
    private const val CONTENT_TYPE = "Content-Type"
    private const val APPLICATION_JSON = "application/json"
    private const val ACCESS_TOKEN = "Bearer ${BuildConfig.ACCESS_TOKEN}"
    private const val AUTHORIZATION = "Authorization"

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class SoftieType

    @Provides
    @Singleton
    @SoftieType
    fun providesSoftieInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request()
        var response = chain.proceed(
            request
                .newBuilder()
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .addHeader(AUTHORIZATION, ACCESS_TOKEN)
                .build()
        )
        response
    }

    @Provides
    @Singleton
    @SoftieType
    fun provideSoftieOkHttpClient(@SoftieType interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
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
    fun provideSoftieRetrofit(@SoftieType okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(APPLICATION_JSON.toMediaType()))
            .build()
}
