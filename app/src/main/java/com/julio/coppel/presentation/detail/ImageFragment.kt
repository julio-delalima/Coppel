package com.julio.coppel.presentation.detail

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.transition.TransitionInflater
import com.google.android.material.color.MaterialColors
import com.julio.coppel.R
import com.julio.coppel.databinding.FragmentImageBinding
import com.julio.coppel.framework.data.remote.model.Image
import com.julio.coppel.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageFragment : Fragment() {

    private lateinit var binding: FragmentImageBinding

    private val viewModel by viewModels<ImageViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val image = arguments?.getParcelable<Image>(ARG_IMAGE)
        ViewCompat.setTransitionName(binding.image, "image_${image?.id}")
        ViewCompat.setTransitionName(binding.alt, "alt_${image?.id}")


        listenData()
        viewModel.load(image!!)

    }

    private fun listenData() {
        viewModel.image.observe(viewLifecycleOwner) {
            binding.image.loadImage(it.src.portrait)

            binding.alt.text = it.alt
            binding.author.text = it.photographer

            Color.parseColor(it.avg_color).let { color ->
                binding.alt.setTextColor(color)
                binding.author.setTextColor(color)
            }
        }

    }

    companion object {
        /**
         * Constante para la imagen.
         */
        const val ARG_IMAGE = "image"
    }
}