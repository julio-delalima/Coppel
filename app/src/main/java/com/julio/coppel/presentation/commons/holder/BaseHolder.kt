package com.julio.coppel.presentation.commons.holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Holder base para la lista de elementos.
 *
 * @param T Tipo de elemento procesado.
 * @constructor Almacena el contexto para ser utilizado en subclases.
 *
 * @param itemView Vista raíz del holder.
 */
abstract class BaseHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    /**
     * Contexto del holder.
     */
    val context: Context = itemView.context

    /**
     * Método que se encarga de pintar el elemento.
     *
     * @param item Elemento por procesar.
     */
    abstract fun bind(item: T?)

    /**
     * Función para ejecutar la lógica al eliminar la vista del adaptador.
     */
    open fun onDetachedFromWindow() {}
}