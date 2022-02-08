package com.julio.coppel.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.julio.coppel.databinding.ItemImageBinding
import com.julio.coppel.databinding.LayoutLoaderBinding
import com.julio.coppel.framework.data.remote.model.Image
import com.julio.coppel.presentation.commons.EndlessAdapter
import com.julio.coppel.presentation.commons.EqualsCallback
import com.julio.coppel.presentation.commons.holder.BaseHolder
import com.julio.coppel.presentation.commons.holder.LoadingHolder

/**
 * Adaptador para la lista de imágenes.
 *
 * @property onSelectImage Listener para la selección de una imagen.
 */
class ImagesAdapter(
    private val onSelectImage: (ItemImageBinding, Image) -> Unit
) : EndlessAdapter<Image, BaseHolder<Image>>(EqualsCallback()) {

    companion object {
        /**
         * Constante para identificar el holder de la imagen.
         */
        private const val IMAGE_HOLDER = 2
    }

    override fun getOwnItemViewType(position: Int): Int {
        return IMAGE_HOLDER
    }

    override fun onCreateOwnViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<Image> {
        return ImageHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ) { binding, holder ->
            getItem(holder.adapterPosition)?.let {
                onSelectImage.invoke(binding, it)
            }
        }
    }

    override fun onCreateLoaderHolder(parent: ViewGroup): BaseHolder<Image> {
        return object : LoadingHolder<Image>(
            LayoutLoaderBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ).root
        ) {}
    }
}