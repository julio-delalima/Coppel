package com.julio.coppel.presentation.commons.holder

import android.view.View

/**
 * Clase para pintar el loader dentro de una lista.
 *
 * @param itemView Binding de la vista raíz.
 */
open class LoadingHolder<T>(itemView: View) : BaseHolder<T>(itemView) {
    override fun bind(item: T?) {}
}