package com.example.androidinternship.ui.screens.todos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.navigation.NavHostController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidinternship.R
import com.example.androidinternship.ui.components.cards.TodoCard

@Composable
fun TodosScreen(
    navController: NavHostController,
    viewModel: TodosViewModel = viewModel()
) {
    val todos by viewModel.todos.collectAsStateWithLifecycle()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("todos/detail?index=null")
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
                    onDelete = { viewModel.deleteTodo(index) },
                    onEdit = {
                        viewModel.startEditing(index)
                        navController.navigate("todos/detail?index=$index")
                    }
                )
            }
        }
    }
}
