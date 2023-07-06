package com.project.napptilus.di

import com.project.data.datasource.PreferencesDataSource
import com.project.data.datasource.PreferencesDataSourceImp
import com.project.data.datasource.RemoteDataSource
import com.project.data.datasource.RemoteDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImp: RemoteDataSourceImp): RemoteDataSource

    @Binds
    abstract fun bindPreferencesDataSource(preferencesDataSourceImp: PreferencesDataSourceImp): PreferencesDataSource
}