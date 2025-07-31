package com.example.androidinternship.domain.interactors.todo

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TodoInteractor private constructor() {
    private val _todos = MutableStateFlow<List<String>>(emptyList())
    val todos: StateFlow<List<String>> = _todos.asStateFlow()

    fun addTodo(todo: String) {
        _todos.value += todo
    }

    fun updateTodo(index: Int, newTodo: String) {
        if (index in _todos.value.indices) {
            val list = _todos.value.toMutableList()
            list[index] = newTodo
            _todos.value = list
        }
    }

    fun deleteTodo(index: Int) {
        _todos.value = _todos.value.filterIndexed { i, _ -> i != index }
    }

    companion object {
        @Volatile
        private var instance: TodoInteractor? = null

        fun getInstance(): TodoInteractor {
            return instance ?: synchronized(this) {
                instance ?: TodoInteractor().also { instance = it }
            }
        }
    }
}
