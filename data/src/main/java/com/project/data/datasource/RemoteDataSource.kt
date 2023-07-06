package com.project.data.datasource

import arrow.core.Either
import com.project.domain.DataWrapper
import com.project.domain.Error
import com.project.domain.OompaLoompa

interface RemoteDataSource {

    suspend fun getOompaLoompasByPage(page: Int): Either<Error, DataWrapper>

    suspend fun getOompaLoompasById(id: Int): Either<Error, OompaLoompa>
}