package com.julio.coppel.framework.source

import com.julio.coppel.data.source.IImagesRemoteSource
import com.julio.coppel.framework.data.remote.ApiService
import com.julio.coppel.framework.data.remote.model.Page
import com.julio.coppel.framework.data.remote.model.PexelImage

class ImagesRemoteSource(private val api: ApiService) : IImagesRemoteSource {
    override suspend fun getImages(): Page = api.getImagesAsync().await()
    override suspend fun getImage(id: String): PexelImage = api.getImageAync(id).await()
}