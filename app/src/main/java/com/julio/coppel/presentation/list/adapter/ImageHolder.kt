package com.julio.coppel.presentation.list.adapter

import android.widget.ImageView
import androidx.core.view.ViewCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.julio.coppel.R
import com.julio.coppel.databinding.ItemImageBinding
import com.julio.coppel.framework.data.remote.model.Image
import com.julio.coppel.presentation.commons.holder.BaseHolder
import com.julio.coppel.utils.loadImage

/**
 * Holder para pintar una imagen.
 *
 * @property binding Vista.
 * @property listener Listener de selección.
 */
class ImageHolder(private val binding: ItemImageBinding, private val listener: (ItemImageBinding, ImageHolder) -> Unit) : BaseHolder<Image>(binding.root) {

    init {
        binding.clickable.setOnClickListener {
            listener.invoke(binding, this)
        }
    }
    override fun bind(item: Image?) {
        item?.let {
            ViewCompat.setTransitionName(binding.image, "image_${it.id}")

            binding.image.loadImage(it.src.medium)
            binding.author.text = context.getString(R.string.format_author,it.photographer)
        }
    }
}