package com.example.androidinternship.domain.repositories

import com.example.androidinternship.ApiClient
import com.example.androidinternship.data.Album
import com.example.androidinternship.data.Photo
import io.ktor.client.call.*
import io.ktor.client.request.*

class AlbumsRepository {
    private val client = ApiClient.client

    suspend fun getAlbums(): List<Album> {
        return client.get("https://jsonplaceholder.typicode.com/albums").body()
    }

    suspend fun getAlbum(albumId: Int): Album {
        return client.get("https://jsonplaceholder.typicode.com/albums/$albumId").body()
    }

    suspend fun getAlbumPhotos(albumId: Int): List<Photo> {
        return client.get("https://jsonplaceholder.typicode.com/albums/$albumId/photos").body()
    }
}