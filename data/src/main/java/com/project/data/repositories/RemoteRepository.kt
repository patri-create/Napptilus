package com.project.data.repositories

import arrow.core.Either
import com.project.data.datasource.RemoteDataSource
import com.project.domain.DataWrapper
import com.project.domain.Error
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    suspend fun getExampleById(id: Int): Either<Error, DataWrapper> {
        return remoteDataSource.getExampleById(id)
    }
}