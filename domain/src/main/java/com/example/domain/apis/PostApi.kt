package com.example.domain.apis

import com.example.domain.data.Post
import com.example.domain.resources.AppSettings.BASE_URL
import com.example.domain.utils.ApiClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

object PostApi {
    private val client: HttpClient = ApiClient.client

    suspend fun getPosts(): List<Post> {
        return client.get("$BASE_URL/posts").body()
    }

    suspend fun getPost(postId: Int): Post {
        return client.get("$BASE_URL/posts/$postId").body()
    }
}