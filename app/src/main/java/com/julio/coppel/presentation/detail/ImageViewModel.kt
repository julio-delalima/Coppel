package com.julio.coppel.presentation.detail

import androidx.lifecycle.ViewModel
import com.julio.coppel.domain.usecase.GetImage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(private val getImage: GetImage) : ViewModel() {

}