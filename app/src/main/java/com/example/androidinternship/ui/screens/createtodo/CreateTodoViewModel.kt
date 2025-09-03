package com.example.androidinternship.ui.screens.createtodo

import androidx.lifecycle.ViewModel
import com.example.androidinternship.ui.screens.todos.TodosViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateTodoViewModel(
    private val editingIndex: Int?,
    private val todosViewModel: TodosViewModel,
) : ViewModel() {

    private var _todoText = MutableStateFlow("")
    val todoText : StateFlow<String> =_todoText.asStateFlow()

    val isEditing: Boolean = editingIndex != null

    init {
        _todoText.value = editingIndex?.let {
            todosViewModel.todos.value?.unwrap()?.getOrNull(it)
        } ?: ""
    }

    fun onTodoTextChanged(newValue: String) {
        _todoText.value = newValue
    }

    fun onSaveClick(onDone: () -> Unit) {
        if (_todoText.value.isBlank()) return

        if (isEditing && editingIndex != null) {
            todosViewModel.updateTodo(editingIndex, _todoText.value)
        } else {
            todosViewModel.addTodo(_todoText.value)
        }

        onDone()
    }
}
