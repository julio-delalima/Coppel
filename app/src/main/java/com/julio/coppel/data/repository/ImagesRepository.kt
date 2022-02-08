package com.julio.coppel.data.repository

import com.julio.coppel.data.source.IImagesRemoteSource

/**
 * Repositorio de imágenes. Ejecuta la lógica local o remota.
 *
 * @property remoteSource Fuente de datos remota.
 */
class ImagesRepository(private val remoteSource: IImagesRemoteSource) {
    /**
     * Método que permite acceder a una página de imágenes.
     *
     * @param items Cantidad de elementos.
     * @param page Página.
     *
     * @return Página consultada.
     */
    suspend fun getImages(items: Int, page: Int) = remoteSource.getImages(items, page)

    /**
     * Método para obtener el detalle de una imagen.
     *
     * @param id Identificador de la imagen.
     * @return Detalle de la imagen.
     */
    suspend fun getImage(id: String) = remoteSource.getImage(id)
}