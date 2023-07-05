package com.project.data.datasource

import arrow.core.Either
import com.project.domain.DataWrapper
import com.project.domain.Error

interface RemoteDataSource {

    suspend fun getExampleById(id: Int): Either<Error, DataWrapper>
}