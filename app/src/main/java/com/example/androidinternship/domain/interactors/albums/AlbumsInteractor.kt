package com.example.androidinternship.domain.interactors.albums

import com.example.androidinternship.data.ALBUMS
import com.example.androidinternship.data.Album
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AlbumsInteractor private constructor() {
    private val _albums = MutableStateFlow(ALBUMS)
    val albums: StateFlow<List<Album>> = _albums.asStateFlow()

    companion object {
        @Volatile
        private var instance: AlbumsInteractor? = null

        fun getInstance(): AlbumsInteractor {
            return instance ?: synchronized(this) {
                instance ?: AlbumsInteractor().also { instance = it }
            }
        }
    }
}