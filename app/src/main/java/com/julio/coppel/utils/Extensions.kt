package com.julio.coppel.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Carga una imagen dentro de un contenedor ImageView.
 *
 * @param url Url de la imagen.
 */
fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}