package com.example.androidinternship.ui.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.androidinternship.data.Album
import com.example.androidinternship.resources.Localization.PHOTOS
import com.example.androidinternship.resources.UIDimentions

@Composable
fun AlbumCard (album: Album, navController: NavController) {
    Card(
        onClick = { navController.navigate("album/${album.id}") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(UIDimentions.smallPadding)
    ) {
        Column(
            modifier = Modifier.padding(UIDimentions.mediumPadding)
        ) {
            Text(album.title)
            Text("$PHOTOS ${album.photos.size}")
        }
    }
}