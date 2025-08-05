package com.example.androidinternship.ui.screens.todos

import androidx.lifecycle.ViewModel
import com.example.androidinternship.domain.interactors.todo.TodoInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TodosViewModel(
) : ViewModel() {

    private val todoInteractor = TodoInteractor.getInstance()

    val todos: StateFlow<List<String>> = todoInteractor.todos

    private val _editingIndex = MutableStateFlow(-1)

    fun deleteTodo(index: Int) {
        todoInteractor.deleteTodo(index)
    }

    fun startEditing(index: Int) {
        _editingIndex.value = index
    }
}
