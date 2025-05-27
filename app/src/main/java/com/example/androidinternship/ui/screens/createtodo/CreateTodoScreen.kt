package com.example.androidinternship.ui.screens.createtodo

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.androidinternship.extentions.hideKeyboardOnTap
import com.example.androidinternship.resources.StateNames.editedTodoIndexState
import com.example.androidinternship.resources.StateNames.editedTodoState
import com.example.androidinternship.resources.StateNames.editingTodoIndexState
import com.example.androidinternship.resources.StateNames.editingTodoState
import com.example.androidinternship.resources.StateNames.newTodoState
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.androidinternship.R
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
                if (editingIndex != null) stringResource(R.string.edit_todo) else stringResource(R.string.add_todo)
            )
        }, navigationIcon = {
            IconButton({ navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBackIosNew, contentDescription = stringResource(R.string.back) )
            }
        })
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(dimensionResource(R.dimen.padding_medium))
        ) {
            OutlinedTextField(value = todoText,
                onValueChange = { todoText = it },
                label = { stringResource(R.string.add_todo) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

            UIButton(text = if (editingIndex != null) stringResource(R.string.save_changes) else stringResource(R.string.add_todo), onClick = {
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