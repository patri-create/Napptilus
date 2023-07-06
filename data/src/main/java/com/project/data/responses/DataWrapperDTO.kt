package com.project.data.responses

import com.project.domain.DataWrapper
import com.project.domain.Favorite
import com.project.domain.OompaLoompa

data class DataWrapperDTO(
    val current: Int?,
    val total: Int?,
    val results: List<ResultDTO>?
)

data class ResultDTO(
    val first_name: String?,
    val last_name: String?,
    val favorite: FavoriteDTO?,
    val gender: String?,
    val image: String?,
    val profession: String?,
    val email: String?,
    val age: Int?,
    val country: String?,
    val height: Int?,
    val id: Int?
)

data class FavoriteDTO(
    val color: String?,
    val food: String?,
    val randomString: String?,
    val song: String?,
)


fun DataWrapperDTO.asDomain(): DataWrapper =
    DataWrapper(
        current,
        total,
        results?.map { it.asDomain() }
    )

fun ResultDTO.asDomain(): OompaLoompa =
    OompaLoompa(
        first_name,
        last_name,
        favorite?.asDomain(),
        gender,
        image,
        profession,
        email,
        age,
        country,
        height,
        id
    )

fun FavoriteDTO.asDomain(): Favorite = 
    Favorite(color, food, randomString, song)