package com.project.napptilus.utils

import com.project.data.ApiService
import com.project.data.responses.FavoriteDTO
import com.project.data.responses.ResultDTO
import com.project.domain.DataWrapper
import com.project.domain.Favorite
import com.project.domain.OompaLoompa

class FakeApiService : ApiService {
    override suspend fun getOompaLoompasById(id: Int) = buildRemoteResultsDTO().first()
    override suspend fun getOompaLoompasByPage(page: Int) = buildRemoteDataWrapperDTO()
}

val sampleDataWrapper = DataWrapper(
    1,
    20,
    buildDomainOompaLommpas(0, 1, 2)
)


fun buildDomainOompaLommpas(vararg id: Int) = id.map {
    OompaLoompa(
        firstName = "FirstName",
        lastName = "LastName",
        buildDomainFavorite(),
        gender = "M",
        "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/1.jpg",
        profession = "Developer",
        "",
        27,
        "",
        180,
        it,
        "Description"
    )
}

fun buildDomainFavorite() = Favorite(
    color = "blue",
    food = "",
    randomString = "",
    song = "",
)