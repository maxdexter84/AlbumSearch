package ru.maxdexter.albumsearch.presenter.fragments.albums

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.maxdexter.albumsearch.R
import ru.maxdexter.albumsearch.databinding.FragmentAlbumsSearchBinding
import ru.maxdexter.albumsearch.presenter.adapters.albumadapter.AlbumAdapter
import ru.maxdexter.albumsearch.presenter.baseclass.BaseFragment

@AndroidEntryPoint
class AlbumsSearchFragment :
    BaseFragment<FragmentAlbumsSearchBinding>(FragmentAlbumsSearchBinding::inflate) {


    private val viewModel: AlbumsSearchViewModel by viewModels()
    private val adapter: AlbumAdapter by lazy {
        AlbumAdapter {
            findNavController().navigate(
                AlbumsSearchFragmentDirections.actionAlbumsSearchFragmentToDetailFragment(
                    it
                )
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loggedObserver()
        loadingObserver()
        userDataObserver()
        logoutListener()
        queryTextListener()
        dataObserver()
        binding.rvAlbumsList.adapter = adapter
        refreshingObserver()
        errorStateObserver()

    }

    private fun errorStateObserver() {
        viewModel.errorState.onEach {
            it?.let {
                Snackbar.make(binding.root, getText(R.string.error_network), Snackbar.LENGTH_LONG)
                    .setAction(
                        getString(
                            R.string.snackbar_repeat_btn_title
                        )
                    ) {
                        viewModel.loadData()
                    }
            }
        }.launchIn(lifecycleScope)
    }

    private fun refreshingObserver() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadData()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun loggedObserver() {
        viewModel.isLogged.onEach {
            it?.let {
                if (!it) findNavController().navigate(AlbumsSearchFragmentDirections.actionAlbumsSearchFragmentToLoginFragment())
            }
        }.launchIn(lifecycleScope)
    }

    private fun loadingObserver() {
        viewModel.loadingState.onEach {
            it?.let { binding.swipeRefresh.isRefreshing = it }

        }.launchIn(lifecycleScope)
    }

    private fun dataObserver() {
        viewModel.albumList.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)
    }

    private fun queryTextListener() {
        binding.edtSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.loadData(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun logoutListener() {
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.log_out -> {
                    viewModel.logout()
                    findNavController().navigate(AlbumsSearchFragmentDirections.actionAlbumsSearchFragmentToLoginFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun userDataObserver() {
        viewModel.userData.onEach {
            it?.let {
                binding.toolbar.title = it.name
                binding.toolbar.subtitle = it.email
            }
        }.launchIn(lifecycleScope)
    }


}