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

@HiltViewModel
class ImagesViewModel @Inject constructor(private val getImages: GetImages) : ViewModel() {

    private val _data = MutableLiveData<List<Image>>()
    val data: LiveData<List<Image>>
        get() = _data

    private val _last = MutableLiveData<Boolean>()
    val last: LiveData<Boolean>
        get() = _last

    private var currentPage = 0

    private var loading = false

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
        const val PAGE_SIZE = 10
    }


}