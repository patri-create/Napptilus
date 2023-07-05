package com.project.data

import com.project.data.responses.DataWrapperDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/v1/public/example/{id}")
    suspend fun getExampleById(@Path("id") id: Int): DataWrapperDTO

}