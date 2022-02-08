package com.julio.coppel.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.julio.coppel.domain.usecase.GetImage
import com.julio.coppel.framework.data.remote.model.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class ImageViewModel : ViewModel() {

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