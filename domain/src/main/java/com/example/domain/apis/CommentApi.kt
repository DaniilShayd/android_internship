package com.example.domain.apis

import com.example.domain.data.Comment
import com.example.domain.resources.AppSettings.BASE_URL
import com.example.domain.utils.ApiClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

object CommentApi {
    private val client: HttpClient = ApiClient.client

    suspend fun getComments(postId: Int): List<Comment> {
        return client.get("$BASE_URL/posts/$postId/comments").body()
    }

    suspend fun getAllComments(): List<Comment> {
        return client.get("$BASE_URL/comments").body()
    }
}