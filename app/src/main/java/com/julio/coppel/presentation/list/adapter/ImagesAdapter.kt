package com.julio.coppel.presentation.list.adapter

import android.view.ViewGroup
import com.julio.coppel.framework.data.remote.model.PexelImage
import com.julio.coppel.presentation.commons.EndlessAdapter
import com.julio.coppel.presentation.commons.EqualsCallback
import com.julio.coppel.presentation.commons.holder.BaseHolder

/**
 * Adaptador para la lista de imágenes.
 *
 * @property onSelectImage Listener para la selección de una imagen.
 */
class ImagesAdapter(
    private val onSelectImage: (PexelImage) -> Unit
) : EndlessAdapter<PexelImage, BaseHolder<PexelImage>>(EqualsCallback()) {

    override fun getOwnItemViewType(position: Int): Int {
        TODO("Not yet implemented")
    }

    override fun onCreateOwnViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<PexelImage> {
        TODO("Not yet implemented")
    }

    override fun onCreateLoaderHolder(parent: ViewGroup): BaseHolder<PexelImage> {
        TODO("Not yet implemented")
    }
}