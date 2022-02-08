package com.julio.coppel.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julio.coppel.domain.usecase.GetImages
import com.julio.coppel.framework.data.remote.model.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View Model para la gestión de estado en la lista de imágenes.
 *
 * @property getImages Caso de uso para las imágenes.
 */
@HiltViewModel
class ImagesViewModel @Inject constructor(private val getImages: GetImages) : ViewModel() {

    /**
     * Instancia observable para las imágenes.
     */
    private val _data = MutableLiveData<List<Image>>()
    val data: LiveData<List<Image>>
        get() = _data

    /**
     * Instancia observable para la última página.
     */
    private val _last = MutableLiveData<Boolean>()
    val last: LiveData<Boolean>
        get() = _last

    /**
     * Última página consumida.
     */
    private var currentPage = 0

    /**
     * Indica si se encuentra cargando.
     */
    private var loading = false

    /**
     * Método que carga la siguiente página de datos.
     */
    fun load() {
        viewModelScope.launch {
            if (_last.value != true && !loading) {

                loading = true
                val next = currentPage + 1

                val response = getImages.invoke(PAGE_SIZE, next)
                val body = response.body()

                if (response.isSuccessful && body != null) {
                    _last.value = body.next_page.isNullOrEmpty()
                    currentPage = body.page
                    if (!body.photos.isNullOrEmpty()) {
                        val data = mutableListOf<Image>()

                        _data.value?.let { data.addAll(it) }
                        body.photos.let { data.addAll(it) }

                        _data.value = data
                    }
                }

                loading = false
            }
        }
    }

    companion object {
        /**
         * Tamaño de la página.
         */
        const val PAGE_SIZE = 10
    }

}