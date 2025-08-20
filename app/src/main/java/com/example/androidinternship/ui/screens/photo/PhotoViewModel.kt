package com.example.androidinternship.ui.screens.photo

import androidx.lifecycle.ViewModel
import com.example.androidinternship.domain.repositories.AlbumsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PhotoViewModel(
    private val albumId: Int,
    private val repository: AlbumsRepository = AlbumsRepository()
) : ViewModel() {

    private val _photos = MutableStateFlow<List<Int>>(emptyList())
    val photos: StateFlow<List<Int>> = _photos.asStateFlow()

    init {
        loadPhotos()
    }

    private fun loadPhotos() {
        val album = repository.getAlbums().firstOrNull { it.id == albumId }
        _photos.value = album?.photos ?: emptyList()
    }
}