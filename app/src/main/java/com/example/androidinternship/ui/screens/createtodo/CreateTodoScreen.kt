package com.example.androidinternship.ui.screens.createtodo

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.androidinternship.resources.UIDimentions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTodoScreen(navController: NavHostController) {
    var todoText by remember { mutableStateOf("") }
    val savedStateHandle = navController.previousBackStackEntry?.savedStateHandle

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Todo") },
                navigationIcon = {
                    IconButton({ navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBackIosNew, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(UIDimentions.mediumPadding)
        ) {
            OutlinedTextField(
                value = todoText,
                onValueChange = { todoText = it },
                label = { Text("Todo") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(UIDimentions.mediumPadding))

            Button(
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    disabledContentColor = MaterialTheme.colorScheme.onSecondary
                ),
                onClick = {
                    if (todoText.isNotBlank()) {
                        savedStateHandle?.set("newTodo", todoText)
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Todo")
            }
        }
    }
}