package com.example.domain.apis

import com.example.domain.data.Album
import com.example.domain.data.Photo
import com.example.domain.resources.AppSettings.BASE_URL
import com.example.domain.utils.ApiClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

object AlbumApi {
    private val client: HttpClient = ApiClient.client

    suspend fun getAlbums(): List<Album> {
        return client.get("$BASE_URL/albums").body()
    }

    suspend fun getAlbum(albumId: Int): Album {
        return client.get("$BASE_URL/albums/$albumId").body()
    }

    suspend fun getAlbumPhotos(albumId: Int): List<Photo> {
        return client.get("$BASE_URL/albums/$albumId/photos").body()
    }
}