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
import com.example.androidinternship.resources.StateNames.EDITED_TODO_INDEX_STATE
import com.example.androidinternship.resources.StateNames.EDITED_TODO_STATE
import com.example.androidinternship.resources.StateNames.EDITING_TODO_INDEX_STATE
import com.example.androidinternship.resources.StateNames.EDITING_TODO_STATE
import com.example.androidinternship.resources.StateNames.NEW_TODO_STATE
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
        derivedStateOf { savedStateHandle.get<String>(NEW_TODO_STATE) }
    }

    val editedTodo by remember {
        derivedStateOf {
            savedStateHandle.get<String>(EDITED_TODO_STATE) to
                    savedStateHandle.get<Int>(EDITED_TODO_INDEX_STATE)
        }
    }

    LaunchedEffect(newTodo) {
        newTodo?.let {
            todos = todos + it
            savedStateHandle.remove<String>(NEW_TODO_STATE)
        }
    }

    LaunchedEffect(editedTodo) {
        editedTodo.let { (editedText, index) ->
            index?.let { idx ->
                if (idx >= 0 && idx < todos.size && !editedText.isNullOrEmpty()) {
                    todos = todos.toMutableList().apply {
                        set(idx, editedText)
                    }
                    savedStateHandle.remove<String>(EDITED_TODO_STATE)
                    savedStateHandle.remove<Int>(EDITED_TODO_INDEX_STATE)
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
                        savedStateHandle[EDITING_TODO_STATE] = todo
                        savedStateHandle[EDITING_TODO_INDEX_STATE] = index
                        navController.navigate(NavRoutes.TODO_DETAIL)
                    }
                )
            }
        }
    }
}