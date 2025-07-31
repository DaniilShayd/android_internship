package com.example.androidinternship.ui.screens.createtodo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.androidinternship.domain.interactors.todo.TodoInteractor
import com.example.androidinternship.resources.StateNames.EDITED_TODO_INDEX_STATE
import com.example.androidinternship.resources.StateNames.EDITED_TODO_STATE
import com.example.androidinternship.resources.StateNames.EDITING_TODO_INDEX_STATE
import com.example.androidinternship.resources.StateNames.EDITING_TODO_STATE
import com.example.androidinternship.resources.StateNames.NEW_TODO_STATE

class CreateTodoViewModel(
    private val editingIndex: Int?
) : ViewModel() {

    val todoInteractor = TodoInteractor.getInstance()

    var todoText by mutableStateOf("")
        private set

    val isEditing: Boolean = editingIndex != null

    init {
        todoText = editingIndex?.let { todoInteractor.todos.value.getOrNull(it) } ?: ""
    }

    fun onTodoTextChanged(newValue: String) {
        todoText = newValue
    }

    fun onSaveClick(onDone: () -> Unit) {
        if (todoText.isBlank()) return

        if (isEditing && editingIndex != null) {
            todoInteractor.updateTodo(editingIndex, todoText)
        } else {
            todoInteractor.addTodo(todoText)
        }

        onDone()
    }
}
