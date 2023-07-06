package com.project.data

import com.project.data.responses.DataWrapperDTO
import com.project.data.responses.ResultDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/napptilus/oompa-loompas")
    suspend fun getOompaLoompasByPage(@Query("page") page: Int): DataWrapperDTO

    @GET("/napptilus/oompa-loompas/{id}")
    suspend fun getOompaLoompasById(@Path("id") id: Int): ResultDTO

}