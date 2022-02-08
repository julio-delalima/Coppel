package com.julio.coppel.framework.data.remote

import com.julio.coppel.framework.data.remote.model.Page
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Declaración para el acceso remoto a datos.
 */
interface ApiService {

    /**
     * Consulta una lista paginada de imágenes.
     *
     * @param items Cantidad de elementos.
     * @param page Página consultada.
     *
     * @return Respuesta.
     */
    @GET("curated")
    suspend fun getImages(@Query("per_page") items: Int, @Query("page") page: Int): Response<Page>
}