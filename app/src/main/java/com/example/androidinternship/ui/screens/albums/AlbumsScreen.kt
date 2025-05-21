package com.example.androidinternship.ui.screens.albums

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import com.example.androidinternship.R
import com.example.androidinternship.data.*
import com.example.androidinternship.resources.UIDimentions


@Composable
fun AlbumsScreen(navController: NavController) {
    val albums by remember {
        mutableStateOf(
            listOf(
                Album(
                    1,
                    "Природа",
                    listOf(R.drawable.photo1, R.drawable.photo2, R.drawable.photo3)
                ),
                Album(
                    2, "Города", listOf(R.drawable.photo4, R.drawable.photo5, R.drawable.photo6)
                ),
                Album(
                    3,
                    "Архитектура",
                    listOf(R.drawable.photo7, R.drawable.photo8, R.drawable.photo9)
                )
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        LazyColumn {
            items(albums) { album ->
                Card(
                    onClick = { navController.navigate("album/${album.id}") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(UIDimentions.smallPadding)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(album.title)
                        Text("Фотографий: ${album.photos.size}")
                    }
                }
            }
        }
    }
}
