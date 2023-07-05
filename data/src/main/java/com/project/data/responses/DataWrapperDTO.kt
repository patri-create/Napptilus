package com.project.data.responses

import com.project.domain.DataWrapper

data class DataWrapperDTO(
    val id: Int?
)

fun DataWrapperDTO.asDomain(): DataWrapper =
    DataWrapper(
        id
    )