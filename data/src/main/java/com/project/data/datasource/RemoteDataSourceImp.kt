package com.project.data.datasource

import arrow.core.Either
import com.project.data.ApiService
import com.project.data.domainCall
import com.project.domain.DataWrapper
import com.project.domain.Error
import com.project.domain.OompaLoompa
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val api: ApiService) : RemoteDataSource {
    override suspend fun getOompaLoompasByPage(page: Int): Either<Error, DataWrapper> = domainCall {
        api.getOompaLoompasByPage(page)
    }

    override suspend fun getOompaLoompasById(id: Int): Either<Error, OompaLoompa> = domainCall {
        api.getOompaLoompasById(id)
    }
}