package com.project.usecases

import arrow.core.Either
import com.project.data.repositories.RemoteRepository
import com.project.domain.DataWrapper
import com.project.domain.Error
import javax.inject.Inject

class GetOompaLoompasByPageUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {

    suspend operator fun invoke(page: Int): Either<Error, DataWrapper> {
        return remoteRepository.getOompaLoompasByPage(page)
    }
}