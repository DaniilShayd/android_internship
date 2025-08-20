package com.example.androidinternship.ui.screens.album

import androidx.lifecycle.ViewModel
import com.example.androidinternship.data.Album
import com.example.androidinternship.domain.repositories.AlbumsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AlbumViewModel(
    albumId: Int,
    repository: AlbumsRepository = AlbumsRepository()
) : ViewModel() {

    private val _album = MutableStateFlow(repository.loadAlbum(albumId = albumId))
    val album: StateFlow<Album?> = _album.asStateFlow()
}