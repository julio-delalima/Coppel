package com.julio.coppel.data.source

import com.julio.coppel.framework.data.remote.model.Page
import com.julio.coppel.framework.data.remote.model.Image
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

    /**
     * Declara el comportamiento para acceder al detalle de una imagen.
     *
     * @param id Identificador de la imagen.
     * @return Imagen.
     */
    suspend fun getImage(id: String): Response<Image>
}