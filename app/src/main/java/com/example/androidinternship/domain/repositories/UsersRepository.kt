package com.example.androidinternship.domain.repositories

import com.example.androidinternship.data.User
import com.example.androidinternship.ApiClient
import io.ktor.client.call.*
import io.ktor.client.request.*

class UsersRepository {
    private val client = ApiClient.client

    suspend fun getUsers(): List<User> {
        return client.get("https://jsonplaceholder.typicode.com/users").body()
    }

    suspend fun getUser(userId: Int): User {
        return client.get("https://jsonplaceholder.typicode.com/users/$userId").body()
    }
}
