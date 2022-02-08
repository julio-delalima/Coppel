package com.julio.coppel.presentation.list.adapter

import com.julio.coppel.databinding.ItemImageBinding
import com.julio.coppel.framework.data.remote.model.Image
import com.julio.coppel.presentation.commons.holder.BaseHolder
import com.julio.coppel.utils.loadImage

/**
 * Holder para pintar una imagen.
 *
 * @property binding Vista.
 */
class ImageHolder(private val binding: ItemImageBinding) : BaseHolder<Image>(binding.root) {

    override fun bind(item: Image?) {
        item?.let {
            binding.image.loadImage(it.src.medium)
        }
    }
}