package com.example.androidinternship.ui.screens.todos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import com.example.androidinternship.navigation.NavRoutes
import com.example.androidinternship.resources.StateNames.editedTodoIndexState
import com.example.androidinternship.resources.StateNames.editedTodoState
import com.example.androidinternship.resources.StateNames.editingTodoIndexState
import com.example.androidinternship.resources.StateNames.editingTodoState
import com.example.androidinternship.resources.StateNames.newTodoState
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.androidinternship.R
import com.example.androidinternship.ui.components.cards.TodoCard

@Composable
fun TodosScreen(
    navController: NavHostController,
    savedStateHandle: SavedStateHandle,
) {
    var todos by rememberSaveable { mutableStateOf(emptyList<String>()) }
    var editingIndex by rememberSaveable { mutableStateOf(-1) }

    val newTodo by remember {
        derivedStateOf { savedStateHandle.get<String>(newTodoState) }
    }

    val editedTodo by remember {
        derivedStateOf {
            savedStateHandle.get<String>(editedTodoState) to
                    savedStateHandle.get<Int>(editedTodoIndexState)
        }
    }

    LaunchedEffect(newTodo) {
        newTodo?.let {
            todos = todos + it
            savedStateHandle.remove<String>(newTodoState)
        }
    }

    LaunchedEffect(editedTodo) {
        editedTodo.let { (editedText, index) ->
            index?.let { idx ->
                if (idx >= 0 && idx < todos.size && !editedText.isNullOrEmpty()) {
                    todos = todos.toMutableList().apply {
                        set(idx, editedText)
                    }
                    savedStateHandle.remove<String>(editedTodoState)
                    savedStateHandle.remove<Int>(editedTodoIndexState)
                    editingIndex = -1
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(NavRoutes.TODO_DETAIL)
            }) {
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add_todo))
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(dimensionResource(R.dimen.padding_medium))
        ) {
            itemsIndexed(todos) { index, todo ->
                TodoCard(
                    todo = todo,
                    onDelete = {
                        todos = todos.filterIndexed { i, _ -> i != index }
                    },
                    onEdit = {
                        editingIndex = index
                        savedStateHandle[editingTodoState] = todo
                        savedStateHandle[editingTodoIndexState] = index
                        navController.navigate(NavRoutes.TODO_DETAIL)
                    }
                )
            }
        }
    }
}