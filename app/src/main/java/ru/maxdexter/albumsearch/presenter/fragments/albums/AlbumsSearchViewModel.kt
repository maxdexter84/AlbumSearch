package ru.maxdexter.albumsearch.presenter.fragments.albums

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.maxdexter.albumsearch.domain.common.Preferences
import ru.maxdexter.albumsearch.domain.usecases.GetAlbumsByArtistNameUseCase
import ru.maxdexter.albumsearch.domain.usecases.GetAlbumsFromDbUseCase
import ru.maxdexter.albumsearch.domain.usecases.GetUserUseCase
import ru.maxdexter.albumsearch.presenter.AppPreferences
import ru.maxdexter.albumsearch.presenter.model.LoggedUser
import ru.maxdexter.albumsearch.presenter.model.UIAlbum
import ru.maxdexter.albumsearch.utils.mapToUIUser
import ru.maxdexter.albumsearch.utils.mapToUiAlbum
import javax.inject.Inject

@HiltViewModel
class AlbumsSearchViewModel @Inject constructor(
    private val preferences: Preferences,
    private val getUserUseCase: GetUserUseCase,
    private val getAlbumsFromDbUseCase: GetAlbumsFromDbUseCase,
    private val getAlbumsByArtistNameUseCase: GetAlbumsByArtistNameUseCase
) :
    ViewModel() {

    private val _userData = MutableStateFlow<LoggedUser?>(null)
    val userData = _userData.asStateFlow()

    private val _isLogged = MutableStateFlow<Boolean?>(null)
    val isLogged = _isLogged.asStateFlow()

    private val _albumList = MutableSharedFlow<List<UIAlbum>>()
    val albumList = _albumList.asSharedFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState = _errorState.asStateFlow()

    private val _loadingState = MutableStateFlow<Boolean?>(null)
    val loadingState = _loadingState.asStateFlow()

    private val _currentQuery = MutableStateFlow("")

    init {
        getLoggedUserData()
        getAlbums()
    }

    private fun getLoggedUserData() {
        if (preferences.getIsLogin(AppPreferences.LOGGED_KEY)) {
            _isLogged.value = true
            val userEmail = preferences.getLoggedUserEmail(AppPreferences.EMAIL_KEY)
            userEmail?.let {
                getUserFromDb(it)
            }
        } else _isLogged.value = false

    }

    private fun getUserFromDb(email: String) {
        viewModelScope.launch {
            getUserUseCase.getUserByEmail(email).map { it.mapToUIUser() }.collect {
                _userData.value = LoggedUser(it.name, it.email)
            }
        }
    }

    fun loadData(artistName: String = _currentQuery.value) {
        setLoadingState(true)
        if (!checkQuery(artistName)) {
            viewModelScope.launch {
                getAlbumsByArtistNameUseCase.getAlbums(artistName).onFailure {
                    setLoadingState(false)
                    Log.e(TAG_ERROR, it.message.toString())
                    _errorState.value = it.message.toString()
                }.onSuccess {
                    Log.e(TAG_ERROR, "success")
                    _errorState.value = null
                    _currentQuery.value = artistName
                }
            }
        } else getAlbums()

    }

    private fun checkQuery(query: String): Boolean {
        return query.uppercase() == _currentQuery.value.uppercase()
    }

    fun logout() {
        preferences.setIsLogin(AppPreferences.LOGGED_KEY, false)
        preferences.setLoggedUserEmail(AppPreferences.EMAIL_KEY, "")
    }

    private fun getAlbums() {
        viewModelScope.launch {
            getAlbumsFromDbUseCase.getAllAlbums().map { list -> list.map { it.mapToUiAlbum() } }
                .collect {
                    _albumList.emit(it)
                }
        }
        setLoadingState(false)
    }

    private fun setLoadingState(state: Boolean) {
        _loadingState.value = state
    }

    companion object {
        const val TAG_ERROR = "TAG_ERROR"
    }


}