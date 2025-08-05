package com.example.androidinternship.ui.screens.photo

import androidx.lifecycle.ViewModel
import com.example.androidinternship.domain.interactors.albums.AlbumsInteractor

class PhotoViewModel(
    private val albumId: Int,
) : ViewModel() {
    private val albumsInteractor = AlbumsInteractor.getInstance()
    val photos = albumsInteractor.albums.value.first { albumId == it.id }.photos
}