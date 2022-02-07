package com.julio.coppel.presentation.list

import androidx.lifecycle.ViewModel
import com.julio.coppel.domain.usecase.GetImages
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(private val getImages: GetImages) : ViewModel() {

    companion object{
        const val PAGE_SIZE = 15
    }


}