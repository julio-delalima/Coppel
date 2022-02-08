package com.julio.coppel.framework.data.remote.model

/**
 * Variantes de una imagen.
 *
 * @property landscape Versión landscape de la imagen.
 * @property large Versión larga de la imagen de tamaño W 940px X H 650px DPR.
 * @property large2x Versión larga aumentada a W 940px X H 650px DPR2.
 * @property medium Imagen escalada de manera proporcional con un tamaño de 350px.
 * @property original Imagen sin cambios.
 * @property portrait Imagen de tamaño W 800px X H 1200px.
 * @property small Imagen escalada a H 130px.
 * @property tiny Versión escalada a W 280px X H 20px
 */
data class Src(
    val landscape: String,
    val large: String,
    val large2x: String,
    val medium: String,
    val original: String,
    val portrait: String,
    val small: String,
    val tiny: String
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Src

        if (landscape != other.landscape) return false
        if (large != other.large) return false
        if (large2x != other.large2x) return false
        if (medium != other.medium) return false
        if (original != other.original) return false
        if (portrait != other.portrait) return false
        if (small != other.small) return false
        if (tiny != other.tiny) return false

        return true
    }

    override fun hashCode(): Int {
        var result = landscape.hashCode()
        result = 31 * result + large.hashCode()
        result = 31 * result + large2x.hashCode()
        result = 31 * result + medium.hashCode()
        result = 31 * result + original.hashCode()
        result = 31 * result + portrait.hashCode()
        result = 31 * result + small.hashCode()
        result = 31 * result + tiny.hashCode()
        return result
    }
}