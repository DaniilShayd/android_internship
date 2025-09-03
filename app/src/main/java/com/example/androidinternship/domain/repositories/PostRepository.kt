package com.example.androidinternship.domain.repositories

import com.example.androidinternship.data.Post
import com.example.androidinternship.ApiClient
import com.example.androidinternship.utils.StatefulData
import io.ktor.client.call.*
import io.ktor.client.request.*

class PostRepository {
    private val client = ApiClient.client

    suspend fun getPosts(): List<Post> {
        return client.get("https://jsonplaceholder.typicode.com/posts").body()
    }

    suspend fun getPost(postId: Int): Post {
        return client.get("https://jsonplaceholder.typicode.com/posts/$postId").body()
    }
}