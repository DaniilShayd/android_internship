package com.example.androidinternship.ui.screens.createtodo

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.androidinternship.extentions.hideKeyboardOnTap
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.androidinternship.R
import com.example.androidinternship.ui.composables.UIButton
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTodoScreen(
    navController: NavHostController,
    viewModel: CreateTodoViewModel = viewModel()
) {
    val todoText by remember { derivedStateOf { viewModel.todoText } }

    Scaffold(
        modifier = Modifier.hideKeyboardOnTap(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (viewModel.isEditing)
                            stringResource(R.string.edit_todo)
                        else
                            stringResource(R.string.add_todo)
                    )
                },
                navigationIcon = {
                    IconButton({ navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBackIosNew,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(dimensionResource(R.dimen.padding_medium))
        ) {
            OutlinedTextField(
                value = todoText,
                onValueChange = viewModel::onTodoTextChanged,
                label = { Text(stringResource(R.string.add_todo)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

            UIButton(
                text = if (viewModel.isEditing)
                    stringResource(R.string.save_changes)
                else
                    stringResource(R.string.add_todo),
                onClick = {
                    viewModel.onSaveClick {
                        navController.popBackStack()
                    }
                }
            )
        }
    }
}

