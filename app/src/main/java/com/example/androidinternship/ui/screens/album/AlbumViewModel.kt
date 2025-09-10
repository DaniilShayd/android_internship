package com.example.androidinternship.ui.screens.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.data.Photo
import com.example.domain.repositories.AlbumsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AlbumViewModel(
    private val albumId: Int,
    private val repository: AlbumsRepository = AlbumsRepository()
) : ViewModel() {

    private val _photos = MutableStateFlow<List<Photo>>(emptyList())
    val photos: StateFlow<List<Photo>> = _photos.asStateFlow()

    init {
        refreshPhotos()
    }

    private fun refreshPhotos() {
        viewModelScope.launch {
            try {
                _photos.value = repository.getAlbumPhotos(albumId)
            } catch (e: Exception) {
                e.printStackTrace()
                _photos.value = emptyList()
            }
        }
    }
}
