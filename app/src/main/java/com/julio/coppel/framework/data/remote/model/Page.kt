package com.julio.coppel.framework.data.remote.model

/**
 * Dataclass para la respuesta de Pexels
 */
data class Page(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: List<PexelImage>,
    val total_results: Int
)