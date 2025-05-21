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
import com.example.androidinternship.resources.UIDimentions
import com.example.androidinternship.ui.components.cards.TodoCard

@Composable
fun TodosScreen(
    navController: NavHostController,
    savedStateHandle: SavedStateHandle,
) {
    var todos by rememberSaveable { mutableStateOf(emptyList<String>()) }

    val newTodo by remember {
        derivedStateOf { savedStateHandle.get<String>("newTodo") }
    }

    LaunchedEffect(newTodo) {
        newTodo?.let {
            todos = todos + it
            savedStateHandle.remove<String>("newTodo")
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(NavRoutes.TODO_DETAIL)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Todo")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(UIDimentions.mediumPadding)
        ) {
            items(todos) { todo ->
                TodoCard(
                    todo = todo,
                    onDelete = {
                        todos = todos.filter {
                            it != todo
                        }
                    }
                )
            }
        }
    }
}

