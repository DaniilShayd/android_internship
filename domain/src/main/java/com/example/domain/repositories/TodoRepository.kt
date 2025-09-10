package com.example.domain.repositories

import com.example.domain.apis.TodoApi
import com.example.domain.data.Todo
import com.example.domain.resources.AppSettings.BASE_URL
import io.ktor.client.call.*
import io.ktor.client.request.*

class TodoRepository {
    suspend fun getTodos(): List<Todo> {
        return TodoApi.getTodos()
    }

    suspend fun getTodo(todoId: Int): Todo {
        return TodoApi.getTodo(todoId)
    }
}
