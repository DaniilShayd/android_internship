package com.example.androidinternship.domain.repositories

import com.example.androidinternship.ApiClient
import com.example.androidinternship.data.Comment
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class CommentsRepository(
    private val client: HttpClient = ApiClient.client
) {
    suspend fun getComments(postId: Int): List<Comment> {
        return client.get("https://jsonplaceholder.typicode.com/posts/$postId/comments").body()
    }

    suspend fun getAllComments(): List<Comment> {
        return client.get("https://jsonplaceholder.typicode.com/comments").body()
    }
}