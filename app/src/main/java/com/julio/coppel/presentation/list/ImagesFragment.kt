package com.julio.coppel.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.julio.coppel.databinding.FragmentImagesBinding
import com.julio.coppel.presentation.list.adapter.ImagesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagesFragment : Fragment() {

    /**
     * Enlace con la vista
     */
    private lateinit var binding: FragmentImagesBinding

    /**
     * View Model para los datos.
     */
    private val viewModel by viewModels<ImagesViewModel>()

    /**
     * Adaptador para las imágenes.
     */
    private val adapter = ImagesAdapter { binding, image ->
        val directions = ImagesFragmentDirections.actionDetail(image)
        val extras = FragmentNavigatorExtras(
            binding.image to "image_${image.id}",
        )

        findNavController().navigate(directions, extras)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImagesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        listenData()
    }

    /**
     * Método que configura la vista.
     */
    private fun setupView() {
        adapter.onLoadMore {
            viewModel.load()
        }

        binding.recycler.let {
            val manager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            it.layoutManager = manager
            adapter.attach(it, manager, ImagesViewModel.PAGE_SIZE)

            postponeEnterTransition()
            it.viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(insets.left, insets.top, insets.right, insets.bottom)

            WindowInsetsCompat.CONSUMED
        }
    }

    /**
     * Método que se registra a los datos observables del View Model.
     */
    private fun listenData() {
        viewModel.data.observe(viewLifecycleOwner) {
            adapter.setList(it.toMutableList())
        }

        viewModel.last.observe(viewLifecycleOwner) {
            adapter.last(it)
        }

        viewModel.load()
    }

}