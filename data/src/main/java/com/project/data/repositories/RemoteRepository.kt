package com.project.data.repositories

import arrow.core.Either
import com.project.data.datasource.RemoteDataSource
import com.project.domain.DataWrapper
import com.project.domain.Error
import com.project.domain.OompaLoompa
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    suspend fun getOompaLoompasByPage(page: Int): Either<Error, DataWrapper> {
        return remoteDataSource.getOompaLoompasByPage(page)
    }

    suspend fun getOompaLoompasById(id: Int): Either<Error, OompaLoompa> {
        return remoteDataSource.getOompaLoompasById(id)
    }
}