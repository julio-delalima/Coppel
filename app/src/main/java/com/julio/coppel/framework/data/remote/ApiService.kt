package com.julio.coppel.framework.data.remote

import com.julio.coppel.framework.data.remote.model.Image
import com.julio.coppel.framework.data.remote.model.Page
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("curated")
    suspend fun getImages(@Query("per_page") items: Int, @Query("page") page: Int): Response<Page>

    @GET("photos/{id}")
    suspend fun getImage(@Path("id") id: String): Response<Image>
}