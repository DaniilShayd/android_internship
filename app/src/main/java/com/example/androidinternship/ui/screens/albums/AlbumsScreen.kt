package com.example.androidinternship.ui.screens.albums

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.androidinternship.R
import com.example.androidinternship.ui.components.cards.AlbumCard

@Composable
fun AlbumsScreen(
    navController: NavHostController,
    viewModel: AlbumsViewModel = viewModel()
) {
    val albums by viewModel.albums.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        if (albums?.isLoading() == true || albums == null) {
            CircularProgressIndicator(
                modifier = Modifier.width(dimensionResource(R.dimen.icon_size_medium)),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
            return
        }
        if (albums?.isError() == true) {
            Text("Альбомы не прогрузились, сорян")
            return
        }

        LazyColumn {
            items(albums?.unwrap() ?: emptyList()) { album ->
                AlbumCard(
                    album = album,
                    navController = navController
                )
            }
        }

    }
}
