package com.example.androidinternship.ui.screens.albums

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.androidinternship.ui.components.cards.AlbumCard
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.androidinternship.R


@Composable
fun AlbumsScreen(
    navController: NavHostController,
    viewModel: AlbumsViewModel = viewModel()
) {
    val albums by viewModel.albums.collectAsStateWithLifecycle()

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column {
                albums.forEach { album ->
                    AlbumCard(
                        album = album,
                        navController = navController
                    )
                }
            }
        }
    }

}
