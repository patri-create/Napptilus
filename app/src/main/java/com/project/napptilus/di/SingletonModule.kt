package com.project.napptilus.di

import android.app.Application
import android.content.Context
import com.project.data.datasource.PreferencesDataSource
import com.project.data.datasource.PreferencesStorage
import com.project.data.datasource.RemoteDataSource
import com.project.data.repositories.PreferencesRepository
import com.project.data.repositories.RemoteRepository
import com.project.napptilus.common.NetworkStatus
import com.project.usecases.FindPreferencesUseCase
import com.project.usecases.SavePreferencesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Provides
    fun providesRemoteRepository(remoteDataSource: RemoteDataSource): RemoteRepository {
        return RemoteRepository(remoteDataSource)
    }

    @Provides
    fun providesPreferencesStorage(@ApplicationContext context: Context): PreferencesStorage {
        return PreferencesStorage(context)
    }

    @Provides
    fun providesPreferencesRepository(preferencesDataSource: PreferencesDataSource): PreferencesRepository {
        return PreferencesRepository(preferencesDataSource)
    }

    @Provides
    fun providesFindPreferencesUseCase(preferencesRepository: PreferencesRepository): FindPreferencesUseCase {
        return FindPreferencesUseCase(preferencesRepository)
    }

    @Provides
    fun providesSavePreferencesUseCase(preferencesRepository: PreferencesRepository): SavePreferencesUseCase {
        return SavePreferencesUseCase(preferencesRepository)
    }

    @Provides
    fun providesNetworkStatus(@ApplicationContext context: Context): NetworkStatus {
        return NetworkStatus(context)
    }
}