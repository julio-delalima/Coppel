package com.julio.coppel.presentation.commons

import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.julio.coppel.presentation.commons.holder.BaseHolder
import com.julio.coppel.presentation.commons.listener.OnMoreScrollListener

/**
 * Adapter base para listas paginadas.
 *
 * Pasos para su uso:
 * 1. Crea un adaptor que que extienda de [EndlessAdapter].
 * 2. Implementa los métodos [getOwnItemViewType], [onCreateOwnViewHolder] y [onCreateLoaderHolder].
 * 3. Genera una nueva instancia de tu adaptador que hereda de [EndlessAdapter].
 * 4. Asigna el escucha para la carga de datos [onLoadMore].
 * 5. Utiliza el método [attach] para configurar la lista con [LinearLayoutManager], [GridLayoutManager] o [StaggeredGridLayoutManager].
 * 6. Opcional: Cuando se conoce que no hay más datos por cargar, utiliza [last] para dejar indicarle al adaptador que deje de mostrar el loader.
 *
 * @param T Tipo de dato procesado.
 * @param VH Vista procesada.
 * @constructor Envía un objeto [DiffUtil.ItemCallback] al padre.
 *
 * @param diff Objeto [DiffUtil.ItemCallback] para procesar la lista.
 * @author Julio.
 * @since 11-08-2021.
 */
abstract class EndlessAdapter<T, VH : BaseHolder<T>>(diff: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, VH>(diff) {

    companion object {
        /**
         * Constante para el holder de carga.
         */
        const val HOLDER_LOADER = -1
    }

    /**
     * Manager de la lista relacionada con el adapter.
     */
    private var manager: RecyclerView.LayoutManager? = null

    /**
     * Listener para la carga de datos.
     */
    private var loadMoreScrollListener: OnMoreScrollListener? = null

    /**
     * Listener para indicar que se necesitan cargar más datos.
     */
    private var loadMoreListener: (() -> Unit)? = null

    /**
     * Indica si va a mostrar el loader o no.
     */
    private var showLoader: Boolean = true

    /**
     * Indica la cantidad de elementos previos al final para agregar el loader.
     */
    var minus: Int = 0

    /**
     * Método para obtener el tipo de vista propio implementado en el adaptador hijo.
     *
     * @param position Posición del elemento.
     * @return Tipo de vista.
     */
    abstract fun getOwnItemViewType(position: Int): Int

    /**
     * Declaración del método para obtener las vistas propias del adapter.
     *
     * @param parent Vista padre.
     * @param viewType Tipo de vista.
     * @return Holder.
     */
    abstract fun onCreateOwnViewHolder(parent: ViewGroup, viewType: Int): VH

    /**
     * Declaración del método para obtener el holder de carga.
     *
     * @param parent Vista padre.
     * @return Holder configurado.
     */
    abstract fun onCreateLoaderHolder(parent: ViewGroup): VH

    /**
     * Configura el adapter y el manager con la lista.
     *
     * @param recycler Lista que se configurará.
     * @param manager Manager para la lista.
     * @param threshold Cantidad de elementos por página.
     * @param runWithLess Indica si se debe lanzar el evento con pocos elementos.
     */
    fun attach(
        recycler: RecyclerView,
        manager: GridLayoutManager,
        threshold: Int = 10,
        runWithLess: Boolean = false
    ) {
        loadMoreScrollListener = OnMoreScrollListener(manager, threshold, runWithLess)
        loadMoreScrollListener?.setListener {
            setLoading()
            loadMoreListener?.invoke()
        }

        recycler.layoutManager = manager
        recycler.addOnScrollListener(loadMoreScrollListener!!)
        recycler.adapter = this

    }

    /**
     * Configura el adapter y el manager con la lista.
     *
     * @param recycler Lista que se configurará.
     * @param manager Manager para la lista.
     * @param threshold Cantidad de elementos por página.
     * @param showLoader Indica si debe mostrar el loader.
     */
    fun attach(
        recycler: RecyclerView,
        manager: LinearLayoutManager,
        threshold: Int = 10,
        showLoader: Boolean = true
    ) {
        this.showLoader = showLoader
        loadMoreScrollListener = OnMoreScrollListener(manager, threshold)
        loadMoreScrollListener?.setListener {
            setLoading()
            loadMoreListener?.invoke()
        }

        recycler.layoutManager = manager
        recycler.addOnScrollListener(loadMoreScrollListener!!)
        recycler.adapter = this

    }

    /**
     * Configura el adapter y el manager con la lista.
     *
     * @param recycler Lista que se configurará.
     * @param manager Manager para la lista.
     * @param threshold Cantidad de elementos por página.
     */
    fun attach(recycler: RecyclerView, manager: StaggeredGridLayoutManager, threshold: Int = 10) {
        loadMoreScrollListener = OnMoreScrollListener(manager, threshold)
        loadMoreScrollListener?.setListener {
            setLoading()
            loadMoreListener?.invoke()
        }

        recycler.layoutManager = manager
        recycler.addOnScrollListener(loadMoreScrollListener!!)
        recycler.adapter = this

    }

    /**
     * Establece el listener para indicar cuando el adapter requiere más datos.
     *
     * @param listener Listener escucha.
     */
    fun onLoadMore(listener: () -> Unit) {
        this.loadMoreListener = listener
    }

    /**
     * Indica al adapter si es la última página por mostrar para evitar que muestre el loader cada vez.
     *
     * @param last Indica que se ha cargado la última página.
     */
    fun last(last: Boolean) {
        loadMoreScrollListener?.last = last
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return when (viewType) {
            HOLDER_LOADER -> onCreateLoaderHolder(parent)
            else -> onCreateOwnViewHolder(parent, viewType)
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) == null)
            HOLDER_LOADER
        else getOwnItemViewType(position)
    }

    /**
     * Le indica al adapter que se están cargando, o no, nuevos datos.
     * El adapter inserta o remueve el loader del final.
     *
     * @param loading Indica si está cargando o no.
     */
    private fun setLoading(loading: Boolean = true) {
        if (!showLoader)
            return

        val list = ArrayList(currentList)

        if (loading) {
            list.add(list.size.minus(minus), null)
            super.submitList(list)
        } else if (list.size > 1) {
            list.removeAt(list.size.minus(1 + minus))
            super.submitList(list)
        }
    }

    /**
     * Envía una nueva lista para calcular la diferencia y mostrarla.
     *
     * @param list La nueva lista por ser mostrada.
     */
    fun setList(list: MutableList<T?>?) {
        if (loadMoreScrollListener?.loading == true) {
            setLoading(false)
            loadMoreScrollListener?.loading = false
        }

        super.submitList(list)
    }

    /**
     * Envía una nueva lista para calcular la diferencia y mostrarla.
     * El commit callback se utiliza para saber cuando la lista fué enviada.
     *
     * @param list Nueva lista.
     * @param commitCallback Escucha.
     */
    fun setList(list: MutableList<T?>?, commitCallback: Runnable?) {
        if (loadMoreScrollListener?.loading == true) {
            setLoading(false)
            loadMoreScrollListener?.loading = false
        }

        super.submitList(list, commitCallback)
    }

    @Deprecated(
        message = "Se debe utilizar la implementación propia.",
        replaceWith = ReplaceWith(
            expression = "this.setList(list)"
        ),
        level = DeprecationLevel.ERROR
    )
    override fun submitList(list: MutableList<T>?) {
        super.submitList(list)
    }

    @Deprecated(
        message = "Utiliza la implementación propia.",
        replaceWith = ReplaceWith(
            expression = "this.setList(list, commitCallback)"
        ),
        level = DeprecationLevel.ERROR
    )
    override fun submitList(list: MutableList<T>?, commitCallback: Runnable?) {
        super.submitList(list, commitCallback)
    }
}