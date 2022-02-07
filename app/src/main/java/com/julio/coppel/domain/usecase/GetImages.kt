package com.julio.coppel.domain.usecase

import com.julio.coppel.data.repository.ImagesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Caso de uso para obtener una página de imágenes.
 *
 * @property repository Repositorio de imágenes.
 */
class GetImages(private val repository: ImagesRepository) {

    /**
     * Ejecuta el caso de uso y consume la lista de imágenes.
     *
     * @return Una vez completada la llamada, retorna el resultado.
     */
    suspend fun invoke() = withContext(Dispatchers.IO) {
        repository.getImages()
    }
}