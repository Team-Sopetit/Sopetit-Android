package com.sopetit.softie.di

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopetit.softie.BuildConfig
import com.sopetit.softie.R
import com.sopetit.softie.data.source.LocalDataSource
import com.sopetit.softie.ui.login.LoginActivity
import com.sopetit.softie.util.toast
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
object RefreshTokenModule {
    private val json = Json { ignoreUnknownKeys = true }
    private const val CONTENT_TYPE = "Content-Type"
    private const val APPLICATION_JSON = "application/json"
    private const val BEARER = "Bearer "
    private const val AUTHORIZATION = "Authorization"
    private const val EXPIRED_TOKEN = 401
    private const val BAD_REQUEST = 400

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RefreshTokenType

    @Provides
    @Singleton
    @RefreshTokenType
    fun providesRefreshTokenInterceptor(
        @ApplicationContext context: Context,
        localDataSource: LocalDataSource
    ): Interceptor = Interceptor { chain ->
        val request = chain.request()
        val response = chain.proceed(
            request
                .newBuilder()
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .addHeader(AUTHORIZATION, BEARER + localDataSource.refreshToken)
                .build()
        )
        when (response.code) {
            EXPIRED_TOKEN, BAD_REQUEST -> {
                Handler(Looper.getMainLooper()).post {
                    context.toast(
                        context.getString(R.string.refresh_error)
                    )
                    context.startActivity(
                        Intent(
                            context,
                            LoginActivity::class.java
                        ).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK) }
                    )
                }
            }
        }
        response
    }

    @Provides
    @Singleton
    @RefreshTokenType
    fun providesRefreshTokenOkHttpClient(@RefreshTokenType interceptor: Interceptor): OkHttpClient =
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
    @RefreshTokenType
    fun providesRefreshTokenRetrofit(@RefreshTokenType okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(APPLICATION_JSON.toMediaType()))
            .build()
}
