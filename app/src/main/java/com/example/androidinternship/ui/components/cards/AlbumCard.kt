package com.example.androidinternship.ui.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.androidinternship.R
import com.example.androidinternship.data.Album

@Composable
fun AlbumCard (album: Album, navController: NavController) {
    Card(
        onClick = { navController.navigate("album/${album.id}") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_small))
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Text(album.title)
            Text("${stringResource(R.string.photos)} ${album.photos.size}")
        }
    }
}