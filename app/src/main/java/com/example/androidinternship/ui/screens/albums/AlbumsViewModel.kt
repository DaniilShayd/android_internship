package com.example.androidinternship.ui.screens.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.data.Album
import com.example.domain.utils.ErrorData
import com.example.domain.utils.StatefulData
import com.example.domain.utils.SuccessData
import com.example.domain.repositories.AlbumsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AlbumsViewModel(
    private val repository: AlbumsRepository = AlbumsRepository()
) : ViewModel() {

    private val _albums = MutableStateFlow<StatefulData<List<Album>>?>(null)
    val albums: StateFlow<StatefulData<List<Album>>?> = _albums.asStateFlow()

    init {
        refreshAlbums()
    }

    private fun refreshAlbums() {
        viewModelScope.launch {
            try {
                val albumsFromApi = repository.getAlbums()
                _albums.value = SuccessData(albumsFromApi)
            } catch (e: Exception) {
                _albums.value = ErrorData(e)
            }
        }
    }
}
