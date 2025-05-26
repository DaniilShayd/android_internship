package com.example.androidinternship.ui.screens.createtodo

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.androidinternship.extentions.hideKeyboardOnTap
import com.example.androidinternship.resources.Localization.ADD_TODO
import com.example.androidinternship.resources.Localization.BACK
import com.example.androidinternship.resources.Localization.EDIT_TODO
import com.example.androidinternship.resources.Localization.SAVE_CHANGES
import com.example.androidinternship.resources.StateNames.editedTodoIndexState
import com.example.androidinternship.resources.StateNames.editedTodoState
import com.example.androidinternship.resources.StateNames.editingTodoIndexState
import com.example.androidinternship.resources.StateNames.editingTodoState
import com.example.androidinternship.resources.StateNames.newTodoState
import com.example.androidinternship.resources.UIDimentions
import com.example.androidinternship.ui.composables.UIButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTodoScreen(navController: NavHostController) {

    val savedStateHandle = navController.previousBackStackEntry?.savedStateHandle

    val editingTodo = savedStateHandle?.get<String>(editingTodoState)
    val editingIndex = savedStateHandle?.get<Int>(editingTodoIndexState)

    var todoText by remember {
        mutableStateOf(editingTodo ?: "")
    }

    Scaffold(modifier = Modifier.hideKeyboardOnTap(), topBar = {
        TopAppBar(title = {
            Text(
                if (editingIndex != null) EDIT_TODO else ADD_TODO
            )
        }, navigationIcon = {
            IconButton({ navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBackIosNew, contentDescription = BACK )
            }
        })
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(UIDimentions.mediumPadding)
        ) {
            OutlinedTextField(value = todoText,
                onValueChange = { todoText = it },
                label = { ADD_TODO },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(UIDimentions.mediumPadding))

            UIButton(text = if (editingIndex != null) SAVE_CHANGES else ADD_TODO, onClick = {
                if (todoText.isNotBlank()) {
                    if (editingIndex != null) {

                        savedStateHandle[editedTodoState] = todoText
                        savedStateHandle[editedTodoIndexState] = editingIndex
                    } else {

                        savedStateHandle?.set(newTodoState, todoText)
                    }
                    navController.popBackStack()
                }
            })
        }
    }
}