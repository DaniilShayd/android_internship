package com.example.domain.apis

import com.example.domain.data.User
import com.example.domain.resources.AppSettings.BASE_URL
import com.example.domain.utils.ApiClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

object UserApi {
    private val client: HttpClient = ApiClient.client

    suspend fun getUsers(): List<User> {
        return client.get("$BASE_URL/users").body()
    }

    suspend fun getUser(userId: Int): User {
        return client.get("$BASE_URL/users/$userId").body()
    }
}