package com.julio.coppel.domain.usecase

import com.julio.coppel.data.repository.ImagesRepository

/**
 * Caso de uso para obtener una página de imágenes.
 *
 * @property repository Repositorio de imágenes.
 */
class GetImages(private val repository: ImagesRepository) {

    /**
     * Ejecuta el caso de uso y consume la lista de imágenes.
     *
     * @param items Cantidad de elementos.
     * @param page Página consultada.
     *
     * @return Una vez completada la llamada, retorna el resultado.
     */
    suspend fun invoke(items: Int, page: Int) = repository.getImages(items, page)
}