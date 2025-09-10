package com.example.domain.repositories

import com.example.domain.apis.UserApi
import com.example.domain.data.User
import com.example.domain.resources.AppSettings.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class UsersRepository {
    suspend fun getUsers(): List<User> {
        return UserApi.getUsers()
    }

    suspend fun getUser(userId: Int): User {
        return UserApi.getUser(userId)
    }
}
