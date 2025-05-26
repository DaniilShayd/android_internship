package com.example.androidinternship.ui.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.androidinternship.resources.UIDimentions

@Composable
fun UIImage(photoRes: Int, onClick: () -> Unit) {
    Image(
        painter = painterResource(photoRes),
        contentDescription = null,
        modifier = Modifier
            .aspectRatio(1f)
            .padding(UIDimentions.smallestPadding)
            .clickable {
                onClick();
            },
        contentScale = ContentScale.Crop
    )
}
