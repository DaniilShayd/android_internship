package com.example.androidinternship.domain.repositories

import com.example.androidinternship.ApiClient
import com.example.androidinternship.data.Todo
import io.ktor.client.call.*
import io.ktor.client.request.*

class TodoRepository {
    private val client = ApiClient.client

    suspend fun getTodos(): List<Todo> {
        return client.get("https://jsonplaceholder.typicode.com/todos").body()
    }

    suspend fun getTodo(todoId: Int): Todo {
        return client.get("https://jsonplaceholder.typicode.com/todos/$todoId").body()
    }
}
