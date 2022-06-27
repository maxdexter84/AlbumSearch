package ru.maxdexter.albumsearch.presenter.fragments.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ru.maxdexter.albumsearch.R
@AndroidEntryPoint
class AlbumsSearchFragment : Fragment() {

    companion object {
        fun newInstance() = AlbumsSearchFragment()
    }

    private lateinit var viewModel: AlbumsSearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_albums_search, container, false)
    }


}