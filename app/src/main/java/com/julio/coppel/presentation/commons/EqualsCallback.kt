package com.julio.coppel.presentation.commons

import androidx.recyclerview.widget.DiffUtil

/**
 * Callback genérico para detectar diferencias entre elementos.
 * Para utilizar esta clase los objetos deben implementar [ComparableContent].
 *
 * @param T Tipo de elemento entre los que se desea comparar.
 *
 * @author Julio.
 * @since MBP-1653.
 */
class EqualsCallback<T> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem?.equals(newItem) == true
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return if (oldItem is ComparableContent)
            (oldItem as ComparableContent).hasTheSameContent(newItem)
        else areItemsTheSame(oldItem, newItem)
    }

    /**
     * Permite comparar el contenido de un objeto con otro.
     */
    interface ComparableContent {
        /**
         * Indica si el objteo dado tiene el mismo contenido.
         * @param item Elemento con el que se hace la comparación-
         * @return Devuelve true si el objeto dado tiene el mismo contenido.
         */
        fun hasTheSameContent(item: Any?): Boolean
    }

    companion object{
        /**
         * Compara el contenido de dos listas de [ComparableContent].
         *
         * @param T Clase de los elementos de la lista.
         * @param first Lista 1.
         * @param second Lista 2.
         * @return Indica si las listas son iguales o no.
         */
        fun <T> isEqual(first: List<T>, second: List<T>): Boolean {

            if (first.size != second.size) {
                return false
            }

            return first.zip(second).all { (x, y) ->
                if (x is ComparableContent && y is ComparableContent)
                    (x as ComparableContent).hasTheSameContent(y)
                else x == y
            }
        }
    }

}