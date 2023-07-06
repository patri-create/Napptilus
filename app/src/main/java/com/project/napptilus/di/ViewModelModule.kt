package com.project.napptilus.di

import com.project.data.repositories.RemoteRepository
import com.project.usecases.GetOompaLoompasByIdUseCase
import com.project.usecases.GetOompaLoompasByPageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun providesGetOompaLoompasByPageUseCase(remoteRepository: RemoteRepository): GetOompaLoompasByPageUseCase {
        return GetOompaLoompasByPageUseCase(remoteRepository)
    }

    @Provides
    @ViewModelScoped
    fun providesGetOompaLoompasByIdUseCase(remoteRepository: RemoteRepository): GetOompaLoompasByIdUseCase {
        return GetOompaLoompasByIdUseCase(remoteRepository)
    }
}