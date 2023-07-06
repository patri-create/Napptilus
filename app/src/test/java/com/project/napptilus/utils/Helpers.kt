package com.project.napptilus.utils

import com.project.data.datasource.RemoteDataSourceImp
import com.project.data.repositories.RemoteRepository
import com.project.data.responses.DataWrapperDTO
import com.project.data.responses.FavoriteDTO
import com.project.data.responses.ResultDTO

fun buildRemoteRepository(): RemoteRepository {
    val remoteDataSource = RemoteDataSourceImp(FakeApiService())
    return RemoteRepository(remoteDataSource)
}

fun buildRemoteDataWrapperDTO() = DataWrapperDTO(
    current = 1,
    total = 20,
    results = buildRemoteResultsDTO(0, 1, 2)
)

fun buildRemoteResultsDTO(vararg id: Int) = id.map {
    ResultDTO(
        first_name = "FirstName",
        last_name = "LastName",
        buildRemoteFavoriteDTO(),
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

fun buildRemoteFavoriteDTO() = FavoriteDTO(
    color = "blue",
    food = "",
    randomString = "",
    song = "",
)