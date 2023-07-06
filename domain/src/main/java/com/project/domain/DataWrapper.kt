package com.project.domain

import java.io.Serializable

data class DataWrapper(
    val current: Int?,
    val total: Int?,
    val results: List<OompaLoompa>?
)

data class OompaLoompa(
    val firstName: String?,
    val lastName: String?,
    val favorite: Favorite?,
    val gender: String?,
    val image: String?,
    val profession: String?,
    val email: String?,
    val age: Int?,
    val country: String?,
    val height: Int?,
    val id: Int?
): Serializable {

    fun getFullName(): String {
        return "$firstName $lastName"
    }
}

data class Favorite(
    val color: String?,
    val food: String?,
    val randomString: String?,
    val song: String?,
): Serializable