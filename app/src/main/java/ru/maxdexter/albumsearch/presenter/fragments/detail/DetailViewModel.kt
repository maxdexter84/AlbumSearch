package ru.maxdexter.albumsearch.presenter.fragments.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.maxdexter.albumsearch.domain.usecases.GetAlbumsFromDbUseCase
import ru.maxdexter.albumsearch.domain.usecases.GetTracksByAlbumIdUseCase
import ru.maxdexter.albumsearch.presenter.fragments.albums.AlbumsSearchViewModel.Companion.TAG_ERROR
import ru.maxdexter.albumsearch.presenter.model.UIAlbum
import ru.maxdexter.albumsearch.presenter.model.UITrack
import ru.maxdexter.albumsearch.utils.mapToUITrack
import ru.maxdexter.albumsearch.utils.mapToUiAlbum
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: GetTracksByAlbumIdUseCase,
    private val getAlbumInfo: GetAlbumsFromDbUseCase
) : ViewModel() {

    private val _trackList = MutableStateFlow<List<UITrack>>(emptyList())
    val trackList = _trackList.asStateFlow()

    private val _albumData = MutableStateFlow<UIAlbum?>(null)
    val albumData = _albumData.asStateFlow()

    fun loadAlbumMetaData(id: String) {
        viewModelScope.launch {
            getAlbumInfo.getAlbumById(id).map { it.mapToUiAlbum() }.collect {
                _albumData.value = it
            }
        }
    }

    fun getTracks(albumId: String) {
        viewModelScope.launch {
            useCase.getTracksByAlbumId(albumId).map { list -> list.map { it.mapToUITrack() } }
                .onSuccess {
                    _trackList.value = it
                }.onFailure {
                    Log.i(TAG_ERROR, it.message.toString())
                }
        }
    }
}