package com.julio.coppel.data.source

import com.julio.coppel.framework.data.remote.model.Page
import com.julio.coppel.framework.data.remote.model.PexelImage

/**
 * Declaración de las funcionalidades para el acceso remoto a las imágenes.
 */
interface IImagesRemoteSource {

    /**
     * Declara el comportamiento para acceder a una página de imágenes.
     *
     * @return Página de imágenes.
     */
    suspend fun getImages(): Page

    /**
     * Declara el comportamiento para acceder al detalle de una imagen.
     *
     * @param id Identificador de la imagen.
     * @return Imagen.
     */
    suspend fun getImage(id: String): PexelImage
}