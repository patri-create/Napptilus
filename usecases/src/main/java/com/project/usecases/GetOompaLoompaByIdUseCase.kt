package com.project.usecases

import arrow.core.Either
import com.project.data.repositories.RemoteRepository
import com.project.domain.Error
import com.project.domain.OompaLoompa
import javax.inject.Inject

class GetOompaLoompasByIdUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {

    suspend operator fun invoke(id: Int): Either<Error, OompaLoompa> {
        return remoteRepository.getOompaLoompasById(id)
    }
}