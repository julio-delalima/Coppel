package com.julio.coppel.framework.data.remote.model

/**
 * Página de imágenes.
 *
 * @property next_page Página siguiente.
 * @property page Actual.
 * @property per_page Cantidad de elementos actuales.
 * @property photos Lista de imágenes.
 * @property total_results Cantidad total de resultados.
 */
data class Page(
    val next_page: String?,
    val page: Int,
    val per_page: Int,
    val photos: List<Image>,
    val total_results: Int
)