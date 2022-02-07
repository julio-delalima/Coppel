package com.julio.coppel.presentation.commons.listener

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * Listener para el manejo de listas lineales paginadas.
 * Exclusivo para [LinearLayoutManager].
 *
 * @author Julio.
 * @since 11-08-2021.
 */
class OnMoreScrollListener() : RecyclerView.OnScrollListener() {

    /**
     * Manager de la lista.
     */
    private var manager: RecyclerView.LayoutManager? = null

    /**
     * Cantidad de elementos en la paginación.
     */
    private var threshold: Int = 10

    /**
     * Listener para indicar que se necesitan cargar más datos.
     */
    private var loadMoreListener: (() -> Unit)? = null

    /**
     * Indica si está cargando.
     */
    var loading: Boolean = false

    /**
     * Indica si es la última página de elementos.
     */
    var last: Boolean = false

    /**
     * Último elemento visible.
     */
    private var lastVisibleItem: Int = 0

    /**
     * Total de items.
     */
    private var totalItems: Int = 0

    /**
     * Indica si se debe lanzar el evento con pocos elementos.
     */
    private var runWithLess = false

    constructor(layoutManager: LinearLayoutManager, threshold: Int) : this() {
        this.manager = layoutManager
        this.threshold = threshold
    }

    constructor(
        layoutManager: GridLayoutManager,
        threshold: Int,
        runWithLess: Boolean = false
    ) : this() {
        this.manager = layoutManager
        this.threshold = threshold * layoutManager.spanCount
        this.runWithLess = runWithLess
    }

    constructor(layoutManager: StaggeredGridLayoutManager, threshold: Int) : this() {
        this.manager = layoutManager
        this.threshold = threshold * layoutManager.spanCount
    }

    /**
     * Establece el listener.
     *
     * @param listener Listener para indicar que cargue más.
     */
    fun setListener(listener: (() -> Unit)) {
        this.loadMoreListener = listener
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (invalidScroll(dx, dy) && !runWithLess) return

        totalItems = manager?.itemCount ?: 0

        when (manager) {
            is StaggeredGridLayoutManager -> {
                val lastVisibleItemPositions =
                    (manager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
                // Get maximum element within the list
                lastVisibleItem = getLastVisibleItem(lastVisibleItemPositions)
            }
            is GridLayoutManager -> {
                lastVisibleItem = (manager as GridLayoutManager).findLastVisibleItemPosition()
            }
            is LinearLayoutManager -> {
                lastVisibleItem = (manager as LinearLayoutManager).findLastVisibleItemPosition()
            }
        }
        if (!loading && !last && processScroll(totalItems, lastVisibleItem,threshold)) {
            loadMoreListener?.invoke()
            loading = true
        }
    }

    /**
     * Valida si es posible avanzar o el scroll no está soportado.
     *
     * @param dx Desplazamiento en x.
     * @param dy Desplazamiento en y.
     * @return Indica si el scroll es inválido.
     */
    private fun invalidScroll(dx: Int, dy: Int): Boolean {
        return when (manager) {
            is LinearLayoutManager -> {
                (manager as LinearLayoutManager).let {
                    if (it.orientation == LinearLayoutManager.VERTICAL) dy <= 0 else dx <= 0
                }
            }
            else -> dy <= 0
        }
    }

    /**
     * Valida si debe proceder la devolución de llamada.
     *
     * @param total Total de elementos.
     * @param lastVisible Último visible.
     * @param threshold Cantidad paginada.
     * @return Indica si debe proceder o no.
     */
    private fun processScroll(total: Int, lastVisible: Int, threshold: Int): Boolean {
        return when (manager) {
            is LinearLayoutManager -> {
                (manager as LinearLayoutManager).let {
                    if (it.orientation == LinearLayoutManager.HORIZONTAL) lastVisible == total-1 else total <= lastVisible + threshold
                }
            }
            else -> total <= lastVisible + threshold
        }
    }

    /**
     * Obtiene el último elemento visible.
     *
     * @param lastVisibleItemPositions Posiciones.
     * @return Elemento.
     */
    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }
}