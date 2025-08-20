package com.example.androidinternship.domain.repositories

import com.example.androidinternship.data.ALBUMS
import com.example.androidinternship.data.Album
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AlbumsRepository {
    fun getAlbums (): List<Album> {
        return ALBUMS;
    }

   fun loadAlbum(albumId : Int) : Album? {
        return ALBUMS.firstOrNull { it.id == albumId }
    }
}