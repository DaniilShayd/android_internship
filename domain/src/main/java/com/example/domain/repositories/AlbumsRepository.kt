package com.example.domain.repositories

import com.example.domain.apis.AlbumApi
import com.example.domain.data.Album
import com.example.domain.data.Photo

class AlbumsRepository {
    suspend fun getAlbums(): List<Album> {
        return AlbumApi.getAlbums()
    }

    suspend fun getAlbum(albumId: Int): Album {
        return AlbumApi.getAlbum(albumId)
    }

    suspend fun getAlbumPhotos(albumId: Int): List<Photo> {
        return AlbumApi.getAlbumPhotos(albumId)
    }
}