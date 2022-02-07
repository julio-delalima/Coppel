package com.julio.coppel.framework.data.remote

import com.julio.coppel.framework.data.remote.model.PexelImage
import com.julio.coppel.framework.data.remote.model.Page
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("curated")
    fun getImagesAsync(): Deferred<Page>

    @GET("photos/{id}")
    fun getImageAync(@Path("id") id: String): Deferred<PexelImage>
}