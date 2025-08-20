package com.example.androidinternship.ui.screens.albums

import androidx.lifecycle.ViewModel
import com.example.androidinternship.data.Album
import com.example.androidinternship.domain.repositories.AlbumsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AlbumsViewModel(
    private val repository: AlbumsRepository = AlbumsRepository()
) : ViewModel() {

    private val _albums = MutableStateFlow(repository.getAlbums())
    val albums: StateFlow<List<Album>> = _albums.asStateFlow()
}