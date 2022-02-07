package com.julio.coppel.domain.usecase

import com.julio.coppel.data.repository.ImagesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Caso de uso para obtener el detalle de una imagen.
 *
 * @property repository Repositorio de imágenes.
 */
class GetImage(private val repository: ImagesRepository) {

    /**
     * Ejecuta el caso de uso y consume la lista de imágenes.
     * @param id Identificador de la canción.
     *
     * @return Una vez completada la llamada, retorna el resultado.
     */
    suspend fun invoke(id: String) = withContext(Dispatchers.IO) {
        repository.getImage(id)
    }
}