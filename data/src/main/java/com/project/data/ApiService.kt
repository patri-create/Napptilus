package com.project.data

import com.project.data.responses.DataWrapperDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/napptilus/oompa-loompas")
    suspend fun getOompaLoompasByPage(@Query("page") page: Int): DataWrapperDTO

}