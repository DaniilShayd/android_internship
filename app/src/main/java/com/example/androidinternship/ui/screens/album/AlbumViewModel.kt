package com.example.androidinternship.ui.screens.album

import androidx.lifecycle.ViewModel
import com.example.androidinternship.data.Album
import com.example.androidinternship.domain.interactors.albums.AlbumsInteractor
import kotlinx.coroutines.flow.StateFlow

class AlbumViewModel : ViewModel(){
    private val albumsInteractor = AlbumsInteractor.getInstance()

    private val albums: StateFlow<List<Album>> = albumsInteractor.albums

    fun getAlbumById(albumId: Int) : Album {
        return albums.value.first {it.id == albumId}
    }
}