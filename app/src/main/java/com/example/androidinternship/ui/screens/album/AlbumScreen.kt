package com.example.androidinternship.ui.screens.album

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.androidinternship.R
import com.example.androidinternship.data.Album
import androidx.compose.ui.*
import androidx.compose.ui.layout.ContentScale
import com.example.androidinternship.resources.UIDimentions

@Composable
fun AlbumScreen(navController: NavController, albumId: Int) {
    val albums = remember {
        listOf(
            Album(
                1, "Природа", listOf(
                    R.drawable.photo1, R.drawable.photo2, R.drawable.photo3
                )
            ),
            Album(
                2, "Города", listOf(
                    R.drawable.photo4, R.drawable.photo5, R.drawable.photo6
                )
            ),
            Album(
                3, "Архитектура", listOf(
                    R.drawable.photo7, R.drawable.photo8, R.drawable.photo9
                )
            )
        )
    }

    val album = albums.find { it.id == albumId } ?: return

    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(album.photos) { photoRes ->
            Image(
                painter = painterResource(photoRes),
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(UIDimentions.smallestPadding)
                    .clickable {
                        val photoIndex = album.photos.indexOf(photoRes)
                        navController.navigate("photo/$photoIndex")
                    },
                contentScale = ContentScale.Crop
            )
        }
    }
}