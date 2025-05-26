package com.example.androidinternship.ui.screens.album

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androidinternship.data.albums
import com.example.androidinternship.resources.Localization.ALBUM_SCREEN
import com.example.androidinternship.ui.components.cards.UIImage

@Composable
fun AlbumScreen(navController: NavController, albumId: Int) {
    Scaffold(
        topBar = { AlbumScreenTopBar(navController) }
    ) { padding ->
        AlbumContent(
            modifier = Modifier.padding(padding),
            navController = navController,
            albumId = albumId
        )
    }
}

@Composable
private fun AlbumContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    albumId: Int
) {
    val albums = remember { albums }
    val album = albums.find { it.id == albumId } ?: return

    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(album.photos) { photoRes ->
            UIImage(
                photoRes = photoRes,
                onClick = {
                    val photoIndex = album.photos.indexOf(photoRes)
                    navController.navigate("photo/$photoIndex")
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AlbumScreenTopBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(ALBUM_SCREEN)
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Back"
                )
            }
        }
    )
}