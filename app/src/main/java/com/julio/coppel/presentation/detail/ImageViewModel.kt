package com.julio.coppel.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.julio.coppel.framework.data.remote.model.Image

/**
 * View Model para la gestión del estado en la vista de detalle.
 */
class ImageViewModel : ViewModel() {

    /**
     * Instancia observable para la imagen.
     */
    private val _image = MutableLiveData<Image>()
    val image: LiveData<Image>
        get() = _image

    /**
     * Carga la información en el View Model.
     *
     * @param image Imagen en detalle.
     */
    fun load(image: Image) {
        _image.value = image
    }
}