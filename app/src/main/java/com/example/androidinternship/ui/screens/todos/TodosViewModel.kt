package com.example.androidinternship.ui.screens.todos

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TodosViewModel(

) : ViewModel() {

    private val _todos = MutableStateFlow<List<String>>(emptyList())
    val todos: StateFlow<List<String>> = _todos.asStateFlow()

    private val _editingIndex = MutableStateFlow(-1)
    val editingIndex: StateFlow<Int> = _editingIndex.asStateFlow()

    fun addTodo(todo: String) {
        _todos.value = _todos.value + todo
    }

    fun updateTodo(index: Int, newTodo: String) {
        if (index in _todos.value.indices) {
            val updated = _todos.value.toMutableList()
            updated[index] = newTodo
            _todos.value = updated
        }
    }

    fun deleteTodo(index: Int) {
        _todos.value = _todos.value.filterIndexed { i, _ -> i != index }
    }

    fun startEditing(index: Int) {
        _editingIndex.value = index
    }

    fun stopEditing() {
        _editingIndex.value = -1
    }
}