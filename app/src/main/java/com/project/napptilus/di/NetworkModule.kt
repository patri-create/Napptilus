package com.project.napptilus.di

import android.content.Context
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.project.data.ApiService
import com.project.napptilus.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun providesOkHttp(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(httpLoggingInterceptor)
        connectTimeout(60, TimeUnit.SECONDS)
        writeTimeout(60, TimeUnit.SECONDS)
        readTimeout(60, TimeUnit.SECONDS)
        hostnameVerifier(hostnameVerifier = { _, _ -> true })
    }.build()

    @Singleton
    @Provides
    @ApiUrl
    fun providesApiURL(): String = BuildConfig.BASE_URL

    @Singleton
    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        @ApiUrl apiUrl: String,
        jacksonConverterFactory: JacksonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(okHttpClient)
            .addConverterFactory(jacksonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun providesJacksonConverterFactory() = JacksonConverterFactory.create(ObjectMapper().apply {
        configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
        configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true)
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        registerKotlinModule()
    })

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}