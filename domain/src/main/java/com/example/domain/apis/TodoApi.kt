package com.example.domain.apis

import com.example.domain.data.Todo
import com.example.domain.resources.AppSettings.BASE_URL
import com.example.domain.utils.ApiClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

object TodoApi {
    private val client: HttpClient = ApiClient.client

    suspend fun getTodos(): List<Todo> {
        return client.get("$BASE_URL/todos").body()
    }

    suspend fun getTodo(todoId: Int): Todo {
        return client.get("$BASE_URL/todos/$todoId").body()
    }
}