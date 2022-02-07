package com.julio.coppel.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.julio.coppel.R
import com.julio.coppel.databinding.FragmentImageBinding
import com.julio.coppel.databinding.FragmentImagesBinding
import com.julio.coppel.presentation.list.adapter.ImagesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagesFragment : Fragment() {

    /**
     * Enlace con la vista
     */
    private lateinit var binding: FragmentImagesBinding

    private val adapter = ImagesAdapter{

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImagesBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = ImagesFragment()
    }
}