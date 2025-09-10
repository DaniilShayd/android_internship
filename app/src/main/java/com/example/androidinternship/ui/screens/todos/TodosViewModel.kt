package com.example.androidinternship.ui.screens.todos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.utils.ErrorData
import com.example.domain.utils.StatefulData
import com.example.domain.utils.SuccessData
import com.example.domain.repositories.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodosViewModel(
    private val repository: TodoRepository = TodoRepository()
) : ViewModel() {

    private val _todos = MutableStateFlow<StatefulData<List<String>>?>(null)
    val todos: StateFlow<StatefulData<List<String>>?> = _todos.asStateFlow()

    private val _editingIndex = MutableStateFlow(-1)

    init {
        refreshTodos()
    }

    private fun refreshTodos() {
        viewModelScope.launch {
            try {
                val todosFromApi = repository.getTodos()
                _todos.value = SuccessData(todosFromApi.map { it.title ?: "" })
            } catch (e: Exception) {
                _todos.value = ErrorData(e)
            }
        }
    }

    fun addTodo(todo: String) {
        val updated = (_todos.value?.unwrap() ?: emptyList()) + todo
        _todos.value = SuccessData(updated)
    }

    fun updateTodo(index: Int, newTodo: String) {
        val current = _todos.value?.unwrap()?.toMutableList() ?: return
        if (index in current.indices) {
            current[index] = newTodo
            _todos.value = SuccessData(current)
        }
    }

    fun deleteTodo(index: Int) {
        val current = _todos.value?.unwrap() ?: return
        _todos.value = SuccessData(current.filterIndexed { i, _ -> i != index })
    }

    fun startEditing(index: Int) {
        _editingIndex.value = index
    }

    fun stopEditing() {
        _editingIndex.value = -1
    }
}
