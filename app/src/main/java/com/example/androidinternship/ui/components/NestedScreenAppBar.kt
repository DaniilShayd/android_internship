package com.example.androidinternship.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androidinternship.R

@Composable
fun NestedScreenAppBar(
    color: Color = MaterialTheme.colorScheme.background,
    navController: NavController? = null,
    onBackClick: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .background(color)
            .height(52.dp)
            .fillMaxWidth(),
    ) {
        val backAction: () -> Unit = {
            onBackClick?.invoke() ?: navController?.popBackStack()
        }

        IconButton(
            onClick = backAction
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = stringResource(R.string.back)
            )
        }
    }
}