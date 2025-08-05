package com.example.androidinternship.ui.screens.albums

import androidx.lifecycle.ViewModel
import com.example.androidinternship.data.Album
import com.example.androidinternship.domain.interactors.albums.AlbumsInteractor
import kotlinx.coroutines.flow.StateFlow

class AlbumsViewModel : ViewModel() {
    private val albumsInteractor = AlbumsInteractor.getInstance()

    val albums: StateFlow<List<Album>> = albumsInteractor.albums
}