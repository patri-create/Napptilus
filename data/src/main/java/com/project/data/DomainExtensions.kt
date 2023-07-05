package com.project.data

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.project.data.responses.DataWrapperDTO
import com.project.data.responses.asDomain
import retrofit2.HttpException
import java.io.IOException
import com.project.domain.Error

suspend fun <T, R> domainCall(action: suspend () -> T): Either<Error, R> = try {
    action().right().toDomain()
} catch (e: Exception) {
    e.toError().left()
}

fun <T, R> T.toDomain(): R {
    val result =  when(this) {
        is DataWrapperDTO -> this.asDomain()
        else -> throw IllegalArgumentException("Unsupported conversion")
    }

    return result as R
}

fun Throwable.toError(): Error = when (this) {
    is IOException -> Error.Connectivity
    is HttpException -> Error.Server(code())
    else -> Error.Unknown(message ?: "")
}