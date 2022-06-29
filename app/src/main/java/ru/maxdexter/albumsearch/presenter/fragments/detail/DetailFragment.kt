package ru.maxdexter.albumsearch.presenter.fragments.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.maxdexter.albumsearch.databinding.FragmentDetailBinding
import ru.maxdexter.albumsearch.presenter.adapters.trackadapter.TrackAdapter
import ru.maxdexter.albumsearch.presenter.baseclass.BaseFragment
import ru.maxdexter.albumsearch.utils.setImage

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val id: String? by lazy {
        arguments?.let { DetailFragmentArgs.fromBundle(it).albumId }
    }

    private val adapter: TrackAdapter by lazy {
        TrackAdapter()
    }

    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        albumInfoObserver()
        trackListObserver()
        binding.rvSongsList.adapter = adapter
        binding.albumToolbar.setNavigationOnClickListener { findNavController().navigateUp() }
    }

    private fun trackListObserver() {
        viewModel.trackList.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)
    }

    private fun albumInfoObserver() {
        viewModel.albumData.onEach {
            it?.let {
                binding.albumToolbar.title = it.name
                binding.albumToolbar.subtitle = "${it.artisName} ${it.date}"
                binding.ivAlbumCover.setImage(it.cover)
            }
        }.launchIn(lifecycleScope)
    }

    private fun loadData() {
        id?.let {
            viewModel.loadAlbumMetaData(it)
            viewModel.getTracks(it)
        }

    }


}