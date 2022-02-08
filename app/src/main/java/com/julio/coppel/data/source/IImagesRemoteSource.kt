package com.julio.coppel.data.source

import com.julio.coppel.framework.data.remote.model.Page
import retrofit2.Response

/**
 * Declaración de las funcionalidades para el acceso remoto a las imágenes.
 */
interface IImagesRemoteSource {

    /**
     * Declara el comportamiento para acceder a una página de imágenes.
     *
     * @param items Cantidad de elementos.
     * @param page Página consultada.
     *
     * @return Página de imágenes.
     */
    suspend fun getImages(items: Int, page: Int): Response<Page>
}