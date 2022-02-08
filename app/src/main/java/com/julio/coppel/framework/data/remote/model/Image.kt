package com.julio.coppel.framework.data.remote.model

import com.julio.coppel.presentation.commons.EqualsCallback

/**
 * Imagen de Pexels.
 *
 * @property avg_color Color promedio.
 * @property height Altura original.
 * @property id Identificador.
 * @property liked Indica si se ha dado me gusta.
 * @property photographer Fotógrafo.
 * @property photographer_id Identificador del fotógrafo.
 * @property photographer_url Url del fotógrafo.
 * @property src Variantes.
 * @property url Url de la imagen.
 * @property width Anchura.
 */
data class Image(
    val avg_color: String,
    val height: Int,
    val id: Int,
    val liked: Boolean,
    val photographer: String,
    val photographer_id: Int,
    val photographer_url: String,
    val src: Src,
    val url: String,
    val width: Int
) : EqualsCallback.ComparableContent {

    override fun hasTheSameContent(item: Any?): Boolean {
        return item is Image &&
                item.avg_color == avg_color &&
                item.photographer == photographer &&
                item.src == src
    }

    override fun equals(other: Any?): Boolean {
        return other is Image &&
                other.id == id
    }

    override fun hashCode(): Int {
        var result = avg_color.hashCode()
        result = 31 * result + height
        result = 31 * result + id
        result = 31 * result + liked.hashCode()
        result = 31 * result + photographer.hashCode()
        result = 31 * result + photographer_id
        result = 31 * result + photographer_url.hashCode()
        result = 31 * result + src.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + width
        return result
    }

}