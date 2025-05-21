package com.example.androidinternship.ui.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import com.example.androidinternship.resources.UIDimentions

@Composable
fun TodoCard(todo: String, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(UIDimentions.smallPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = todo)
        IconButton(onClick = onDelete) {
            Icon(
                Icons.Default.Check, contentDescription = "Delete"
            )
        }
    }
}