package com.julio.coppel.framework.source

import com.julio.coppel.data.source.IImagesRemoteSource
import com.julio.coppel.framework.data.remote.ApiService
import com.julio.coppel.framework.data.remote.model.Page
import retrofit2.Response

/**
 * Especificación para la fuente de datos remota de imágenes.
 *
 * @property api Api.
 */
class ImagesRemoteSource(private val api: ApiService) : IImagesRemoteSource {

    override suspend fun getImages(items: Int, page: Int): Response<Page> = api.getImages(items, page)

}