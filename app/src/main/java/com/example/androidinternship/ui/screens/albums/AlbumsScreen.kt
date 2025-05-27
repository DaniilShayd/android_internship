package com.example.androidinternship.ui.screens.albums

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.androidinternship.ui.components.cards.AlbumCard
import androidx.navigation.NavController
import com.example.androidinternship.data.albums
import androidx.compose.ui.res.dimensionResource
import com.example.androidinternship.R


@Composable
fun AlbumsScreen(navController: NavController) {
    val albums by remember { mutableStateOf( albums) }

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
