package com.example.pixabay

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {

    @GET("/api/")
    fun getImage(@Query("q") keyWords: String,
                 @Query("key") key: String = "31991199-ee4e0b5a2d554bc1078dd2bfa",
                 @Query("page") page: Int,
                 @Query("per_page") perpage: Int = 5
    ): Call<PixaModel>

}