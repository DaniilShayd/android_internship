package com.example.androidinternship.ui.screens.album

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.androidinternship.R
import com.example.androidinternship.ui.components.cards.UIImage

@Composable
fun AlbumScreen(
    navController: NavController,
    albumId: Int,
    viewModel: AlbumViewModel = AlbumViewModel(albumId = albumId)
) {
    Scaffold(
        topBar = { AlbumScreenTopBar(navController) }
    ) { padding ->
        AlbumContent(
            modifier = Modifier.padding(padding),
            navController = navController,
            albumId = albumId,
            viewModel = viewModel,
        )
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
private fun AlbumContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    albumId: Int,
    viewModel: AlbumViewModel,
) {
    val photos by viewModel.photos.collectAsStateWithLifecycle()

    if (photos.isEmpty()) {
        CircularProgressIndicator(modifier = Modifier.width(dimensionResource(R.dimen.icon_size_medium)))
        return
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(photos) { photo ->
            UIImage(
                photoUrl = photo.url ?: "",
                onClick = {
                    val photoIndex = photos.indexOf(photo)
                    navController.navigate("photo/$albumId/$photoIndex")
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
            Text(stringResource(R.string.album_screen))
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